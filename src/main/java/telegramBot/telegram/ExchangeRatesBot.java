package telegramBot.telegram;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramBot.dao.services.UserService;
import telegramBot.telegram.commands.BaseCommand;
import telegramBot.telegram.commands.CommandManager;

@Component
@Log4j
public class ExchangeRatesBot extends TelegramLongPollingBot {

    @Autowired
    private CommandManager manager;

    @Value("${bot_name}")
    private String botName;
    @Value("${bot_token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.info("Received message: \"" + message.getText() + "\" from " + message.getChatId());
        BaseCommand command = manager.getCommand(message.getText(), message.getChatId());
        command.send(this, update);
    }
}
