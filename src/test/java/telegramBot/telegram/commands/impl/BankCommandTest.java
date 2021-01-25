package telegramBot.telegram.commands.impl;

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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.currency.data.ExchangeRate;
import telegramBot.currency.service.keyboard.KeyboardService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankCommandTest {
    private static final String MESSAGE_TEXT = "Выберите источник данных";

    private static final Long CHAT_ID = 1L;

    @Captor
    private ArgumentCaptor<SendMessage> messageArgumentCaptor;

    @Mock
    private KeyboardService keyboardService;

    @InjectMocks
    private BankCommand testingInstance;

    @Test
    public void shouldSendMessageWithBankOptions() throws TelegramApiException {
        //Given
        AbsSender sender = mock(AbsSender.class);
        Update update = createTelegramUpdateMock();
        ReplyKeyboard replyKeyboard = mock(ReplyKeyboard.class);

        when(keyboardService.getBankKeyboard()).thenReturn(replyKeyboard);

        //When
        testingInstance.send(sender, update);

        //Then
        verify(sender).execute(messageArgumentCaptor.capture());

        SendMessage argument = messageArgumentCaptor.getValue();
        assertEquals(CHAT_ID.toString(), argument.getChatId());
        assertEquals(MESSAGE_TEXT, argument.getText());
        assertEquals(replyKeyboard, argument.getReplyMarkup());

    }


    private Update createTelegramUpdateMock() {
        Message message = createMessageMock();
        Update update = mock(Update.class);
        when(update.getMessage()).thenReturn(message);
        return update;
    }

    private Message createMessageMock() {
        Message message = mock(Message.class);
        when(message.getChatId()).thenReturn(CHAT_ID);
        return message;
    }
}