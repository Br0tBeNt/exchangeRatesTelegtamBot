package telegramBot.dao.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import telegramBot.dao.entity.User;
import telegramBot.dao.repositories.UserRepository;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {
    private static final long TELEGRAM_ID = 1L;
    private static final String USD = "USD";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultUserService testingInstance;

    @Test
    public void shouldSaveCurrencySelection() {
        //Given
        User user = mock(User.class);
        when(userRepository.findUserByTelegramId(TELEGRAM_ID)).thenReturn(Optional.of(user));
        doNothing().when(user).setCurrencySelection(USD);

        //When
        testingInstance.setCurrencySelection(TELEGRAM_ID, USD);

        //Then
        verify(user).setCurrencySelection(USD);
        verify(userRepository).save(user);
    }

}