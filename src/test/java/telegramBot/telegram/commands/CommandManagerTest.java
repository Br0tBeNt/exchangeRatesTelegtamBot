package telegramBot.telegram.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import telegramBot.dao.repositories.UserRepository;
import telegramBot.dao.services.UserService;
import telegramBot.telegram.commands.impl.BankCommand;
import telegramBot.telegram.commands.impl.ExchangeCommand;
import telegramBot.telegram.commands.impl.StartCommand;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CommandManagerTest {

    @Mock
    private ExchangeCommand exchangeCommand;

    @Mock
    private BankCommand bankCommand;

    @Mock
    private StartCommand startCommand;

    @Mock
    private UserService userService;

    @InjectMocks
    private CommandManager manager;


    @Test
    public void getStartCommand() {
        BaseCommand start = manager.getCommand("/start", 1L);
        assertTrue(start instanceof StartCommand);
    }

    @Test
    public void getBankCommand() {
        BaseCommand exchange = manager.getCommand("\u0024 USD", 1L);
        assertTrue(exchange instanceof BankCommand);
    }

    @Test
    public void getExchangeCommand() {
        BaseCommand bank = manager.getCommand("ПриватБанк", 1L);
        assertTrue(bank instanceof ExchangeCommand);
    }

    @Test
    public void getDefaultCommand() {
        BaseCommand bank = manager.getCommand("bad", 1L);
        assertTrue(bank instanceof StartCommand);


    }
}