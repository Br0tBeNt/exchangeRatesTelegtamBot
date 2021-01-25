package telegramBot.telegram.commands.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.currency.data.ExchangeRate;
import telegramBot.currency.manage.CurrencyParserFactory;
import telegramBot.currency.service.CurrencyParser;
import telegramBot.currency.service.impl.PrivatBankParser;
import telegramBot.currency.service.keyboard.KeyboardService;
import telegramBot.dao.services.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeCommandTest {
    private static final String MESSAGE_TEXT = "Покупка: %s\n" + "Продажа: %s";

    private static final Long CHAT_ID = 1L;
    private static final String USER_NAME = "Tim";

    private static final String BASE_CURRENCY = "UAH";
    private static final String CURRENCY = "USD";
    private static final double SALE_RATE = 28.40;
    private static final double PURCHASE_RATE = 28.20;

    @Captor
    private ArgumentCaptor<SendMessage> messageArgumentCaptor;

    @Mock
    private KeyboardService keyboardService;

    @Mock
    private UserService userService;

    @Mock
    private CurrencyParserFactory parserFactory;

    @InjectMocks
    private ExchangeCommand testingInstance;

    @Test
    public void shouldSendMessageWithExchangeOptions() throws TelegramApiException {
        //Given
        AbsSender sender = mock(AbsSender.class);
        ReplyKeyboard replyKeyboard = mock(ReplyKeyboard.class);
        Update update = createTelegramUpdateMock();
        CurrencyParser currencyParser = createPrivatBankParserMock();

        when(keyboardService.getStartKeyboard()).thenReturn(replyKeyboard);
        when(userService.getCurrencySelection(any())).thenReturn(CURRENCY);
        when(parserFactory.findProvider(any())).thenReturn(currencyParser);

        //When
        testingInstance.send(sender, update);

        //Then
        verify(sender).execute(messageArgumentCaptor.capture());

        SendMessage argument = messageArgumentCaptor.getValue();
        assertEquals(CHAT_ID.toString(), argument.getChatId());
        assertEquals(String.format(MESSAGE_TEXT, PURCHASE_RATE, SALE_RATE), argument.getText());
        assertEquals(replyKeyboard, argument.getReplyMarkup());
    }

    private Update createTelegramUpdateMock() {
        Message message = createMessageMock();
        Update update = mock(Update.class);
        when(update.getMessage()).thenReturn(message);
        return update;
    }

    private Message createMessageMock() {
        User user = createUserMock();

        Message message = mock(Message.class);
        when(message.getChatId()).thenReturn(CHAT_ID);
        when(message.getFrom()).thenReturn(user);
        return message;
    }

    private User createUserMock() {
        User user = mock(User.class);
        when(user.getUserName()).thenReturn(USER_NAME);
        return user;
    }

    private ExchangeRate createRateMock() {
        return ExchangeRate.builder()
                .baseCurrency(BASE_CURRENCY)
                .currency(CURRENCY)
                .saleRate(SALE_RATE)
                .purchaseRate(PURCHASE_RATE)
                .build();
    }

    private CurrencyParser createPrivatBankParserMock() {
        ExchangeRate rate = createRateMock();
        CurrencyParser parser = mock(PrivatBankParser.class);
        when(parser.getRate(BASE_CURRENCY, CURRENCY)).thenReturn(rate);

        return parser;
    }
}