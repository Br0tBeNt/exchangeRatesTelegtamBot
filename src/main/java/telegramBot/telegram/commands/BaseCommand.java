package telegramBot.telegram.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface BaseCommand {

    void send(AbsSender sender, Update update);
}
