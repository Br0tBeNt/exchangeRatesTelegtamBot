package telegramBot.dao.services.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegramBot.currency.data.ExchangeRateConstants;
import telegramBot.dao.entity.User;
import telegramBot.dao.repositories.UserRepository;
import telegramBot.dao.services.UserService;
import java.util.Optional;

@Service
@Log4j2
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUserIfNot(Message message) {
        if (findUserByTelegramId(message.getChatId()).isEmpty()) {
            createUser(User.builder()
                    .name(message.getFrom().getFirstName())
                    .surname(message.getFrom().getLastName())
                    .telegramId(message.getChatId())
                    .build());
        }
    }


    @Override
    public void setCurrencySelection(Long telegramId, String currency) {
        User user = userRepository.findUserByTelegramId(telegramId).orElseThrow();
        user.setCurrencySelection(currency);
        userRepository.save(user);
    }

    @Override
    public String getCurrencySelection(Long telegramId) {
        Optional<User> user = findUserByTelegramId(telegramId);
        if (user.isPresent()) {
            return user.get().getCurrencySelection();
        }
        log.warn("User didn't choice exchange currency, got default value");
        return ExchangeRateConstants.USD;
    }

    private Optional<User> findUserByTelegramId(Long telegramId) {
        return userRepository.findUserByTelegramId(telegramId);
    }

    private void createUser(User user) {
        userRepository.save(user);
        log.info("Created user: " + user);
    }


}
