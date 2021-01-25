package telegramBot.currency.service.keyboard;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import telegramBot.currency.data.ExchangeRateConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Data
public class KeyboardService {

    private boolean selective = true;
    private boolean resize = true;
    private boolean oneTime = false;

    public ReplyKeyboard getStartKeyboard() {

        List<String> cur = new ArrayList<>();
        cur.add(ExchangeRateConstants.USD);
        cur.add(ExchangeRateConstants.EUR);
        cur.add(ExchangeRateConstants.RUB);

        return createReply(cur);
    }

    public ReplyKeyboard getBankKeyboard() {

        List<String> cur = new ArrayList<>();
        cur.add(ExchangeRateConstants.PB);
        cur.add(ExchangeRateConstants.OB);
        cur.add(ExchangeRateConstants.HB);

        return createReply(cur);
    }

    private ReplyKeyboardMarkup createReply(List<String> names) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(selective);
        replyKeyboardMarkup.setResizeKeyboard(resize);
        replyKeyboardMarkup.setOneTimeKeyboard(oneTime);

        KeyboardRow row = new KeyboardRow();
        names.forEach(row::add);

        replyKeyboardMarkup.setKeyboard(new ArrayList<>(Arrays.asList(row)));

        return replyKeyboardMarkup;
    }
}