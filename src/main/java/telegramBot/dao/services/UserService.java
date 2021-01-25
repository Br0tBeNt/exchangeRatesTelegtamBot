package telegramBot.dao.services;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserService {

    void createUserIfNot(Message message);

    void setCurrencySelection(Long telegramId, String currency);

    String getCurrencySelection(Long telegramId);
}
