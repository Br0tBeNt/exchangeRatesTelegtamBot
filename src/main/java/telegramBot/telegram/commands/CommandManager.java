package telegramBot.telegram.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telegramBot.currency.data.ExchangeRateConstants;
import telegramBot.dao.services.UserService;
import telegramBot.telegram.commands.impl.BankCommand;
import telegramBot.telegram.commands.impl.ExchangeCommand;
import telegramBot.telegram.commands.impl.StartCommand;

@Component
public class CommandManager {

    @Autowired
    private UserService userService;

    @Autowired
    private ExchangeCommand exchangeCommand;

    @Autowired
    private StartCommand startCommand;

    @Autowired
    private BankCommand bankCommand;


    public BaseCommand getCommand(String text, Long id) {

        switch (text) {
            case "/start":
                startCommand.setFirstEnter(true);
                return startCommand;

            case ExchangeRateConstants.USD:
            case ExchangeRateConstants.EUR:
            case ExchangeRateConstants.RUB:
                return setCurrencyStateManager(id, text.split(" ")[1]);

            case ExchangeRateConstants.PB:
            case ExchangeRateConstants.OB:
            case ExchangeRateConstants.HB:
                exchangeCommand.setProvider(text);
                return exchangeCommand;

            default:
                startCommand.setFirstEnter(false);
                return startCommand;
        }
    }

    private BankCommand setCurrencyStateManager(Long id, String currency) {
        userService.setCurrencySelection(id, currency);
        return bankCommand;
    }
}

