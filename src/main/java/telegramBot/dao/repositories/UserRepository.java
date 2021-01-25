package telegramBot.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import telegramBot.dao.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByTelegramId(Long telegramId);
}
