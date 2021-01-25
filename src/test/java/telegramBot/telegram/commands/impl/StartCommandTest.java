package telegramBot.telegram.commands.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
import telegramBot.currency.service.keyboard.KeyboardService;
import telegramBot.dao.repositories.UserRepository;
import telegramBot.dao.services.UserService;
import telegramBot.dao.services.impl.DefaultUserService;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StartCommandTest {
    private static final String DEFAULT_MESSAGE_TEXT = "Выберите валюту, которую будем искать";
    private static final String FIRST_MESSAGE_TEXT = "Добрый день, %s!\n" +"Выберите валюту, которую будем искать";

    private static final Long CHAT_ID = 1L;
    private static final String USER_NAME = "John";

    @Captor
    private ArgumentCaptor<SendMessage> messageArgumentCaptor;

    @Mock
    KeyboardService keyboardService;

    @Mock
    UserService userService;

    @InjectMocks
    private StartCommand testingInstance;

    @Test
    public void shouldSendMessageWithStartOptionsFirstEnter() throws TelegramApiException {

        AbsSender sender = mock(AbsSender.class);
        Update update = createTelegramUpdateMock();
        ReplyKeyboard replyKeyboard = mock(ReplyKeyboard.class);
        testingInstance.setFirstEnter(true);

        when(keyboardService.getStartKeyboard()).thenReturn(replyKeyboard);
        //When
        testingInstance.send(sender, update);

        //Then
        verify(sender).execute(messageArgumentCaptor.capture());

        SendMessage argument = messageArgumentCaptor.getValue();

        assertEquals(CHAT_ID.toString(), argument.getChatId());
        assertEquals(String.format(FIRST_MESSAGE_TEXT,USER_NAME), argument.getText());
        assertEquals(replyKeyboard, argument.getReplyMarkup());
    }

    @Test
    public void shouldSendMessageWithStartOptionsDefault() throws TelegramApiException {

        AbsSender sender = mock(AbsSender.class);
        Update update = createTelegramUpdateMock();
        ReplyKeyboard replyKeyboard = mock(ReplyKeyboard.class);

        when(keyboardService.getStartKeyboard()).thenReturn(replyKeyboard);
        //When
        testingInstance.send(sender, update);

        //Then
        verify(sender).execute(messageArgumentCaptor.capture());

        SendMessage argument = messageArgumentCaptor.getValue();

        assertEquals(CHAT_ID.toString(), argument.getChatId());
        assertEquals(DEFAULT_MESSAGE_TEXT, argument.getText());
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
}
