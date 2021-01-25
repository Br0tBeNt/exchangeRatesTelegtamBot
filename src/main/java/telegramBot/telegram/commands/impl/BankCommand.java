package telegramBot.telegram.commands.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.currency.service.keyboard.KeyboardService;
import telegramBot.telegram.commands.BaseCommand;

@Component
@Log4j
public class BankCommand implements BaseCommand {

    @Autowired
    private KeyboardService keyboardService;

    private static final String MESSAGE_TEXT = "Выберите источник данных";

    @Override
    public void send(AbsSender sender, Update update) {
        Message message = update.getMessage();
        ReplyKeyboard keyboard = keyboardService.getBankKeyboard();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(message.getChatId()))
                .text(MESSAGE_TEXT)
                .replyMarkup(keyboard)
                .build();

        try {
            sender.execute(sendMessage);
            log.info("Send BankCommand to "+sendMessage.getChatId());
        } catch (TelegramApiException e) {
            log.error("Failed send BankCommand");
        }
    }
}
