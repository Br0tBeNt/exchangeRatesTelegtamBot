package telegramBot;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j
public class TelegramBotApplication {

    public static void main(String[] args) {

        SpringApplication.run(TelegramBotApplication.class, args);
        log.info("Application started");
    }
}
