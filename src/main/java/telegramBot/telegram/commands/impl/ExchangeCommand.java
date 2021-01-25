package telegramBot.telegram.commands.impl;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.currency.data.ExchangeRate;
import telegramBot.currency.manage.CurrencyParserFactory;
import telegramBot.currency.service.CurrencyParser.RateProvider;
import telegramBot.currency.service.keyboard.KeyboardService;
import telegramBot.dao.services.UserService;
import telegramBot.telegram.commands.BaseCommand;

@Component
@Setter
@Log4j
public class ExchangeCommand implements BaseCommand {

    @Autowired
    private UserService userService;

    @Autowired
    private KeyboardService keyboardService;

    @Autowired
    private CurrencyParserFactory parserFactory;

    private String provider;

    @Override
    public void send(AbsSender sender, Update update) {
        Message message = update.getMessage();

        ReplyKeyboard keyboard = keyboardService.getStartKeyboard();

        String currency = userService.getCurrencySelection(message.getChatId());

        ExchangeRate rate = parserFactory.findProvider(RateProvider.of(provider)).getRate("UAH", currency);

        log.info("Got "+currency+" rate from "+ provider +" by "+message.getFrom().getUserName());

        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(message.getChatId()))
                .text(rate.toString())
                .replyMarkup(keyboard)
                .build();

        try {
            sender.execute(sendMessage);
            log.info("Send ExchangeCommand to "+sendMessage.getChatId());
        } catch (TelegramApiException e) {
            log.error("Failed send ExchangeCommand");
        }
    }
}
