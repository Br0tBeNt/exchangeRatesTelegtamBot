package telegramBot.currency.service.keyboard;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import telegramBot.currency.data.ExchangeRateConstants;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class KeyboardServiceTest {

    @Test
    void getStartKeyboard() {
        KeyboardService keyboardService = new KeyboardService();
        ReplyKeyboardMarkup keyboardMarkup = (ReplyKeyboardMarkup) keyboardService.getStartKeyboard();

        assertEquals(keyboardMarkup.getKeyboard().get(0).get(0).getText(), ExchangeRateConstants.USD);
        assertEquals(keyboardMarkup.getKeyboard().get(0).get(1).getText(), ExchangeRateConstants.EUR);
        assertEquals(keyboardMarkup.getKeyboard().get(0).get(2).getText(), ExchangeRateConstants.RUB);
    }

    @Test
    void getBankKeyboard() {
        KeyboardService keyboardService = new KeyboardService();
        ReplyKeyboardMarkup keyboardMarkup = (ReplyKeyboardMarkup) keyboardService.getBankKeyboard();

        assertEquals(keyboardMarkup.getKeyboard().get(0).get(0).getText(), ExchangeRateConstants.PB);
        assertEquals(keyboardMarkup.getKeyboard().get(0).get(1).getText(), ExchangeRateConstants.OB);
        assertEquals(keyboardMarkup.getKeyboard().get(0).get(2).getText(), ExchangeRateConstants.HB);
    }
}