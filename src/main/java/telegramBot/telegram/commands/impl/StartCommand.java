package telegramBot.telegram.commands.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.currency.service.keyboard.KeyboardService;
import telegramBot.dao.services.UserService;
import telegramBot.telegram.commands.BaseCommand;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j
public class StartCommand implements BaseCommand {

    @Autowired
    private KeyboardService keyboardService;

    @Autowired
    private UserService userService;

    private boolean firstEnter = false;


    private final String firstText = "Добрый день, %s!\n" +
            "Выберите валюту, которую будем искать";

    private final String defaultText = "Выберите валюту, которую будем искать";


    @Override
    public void send(AbsSender sender, Update update) {
        Message message = update.getMessage();

        ReplyKeyboard keyboard = keyboardService.getStartKeyboard();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(message.getChatId()))
                .text(firstEnter ? String.format(firstText,message.getFrom().getUserName()) : defaultText)
                .replyMarkup(keyboard)
                .build();

        userService.createUserIfNot(message);

        try {
            sender.execute(sendMessage);
            log.info("Send StartCommand to "+sendMessage.getChatId());
        } catch (TelegramApiException e) {
            log.error("Failed send StartCommand");
        }
    }

    @Override
    public String toString(){

        return "StartCom: "+firstEnter;
    }
}
