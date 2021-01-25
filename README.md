#Telegram Exchange Rate Bot
___


####A simple to use telegram bot for searching currency rates from different sources.

##LICENSE

This project is licensed under the Apache 2.0 license

Details in the LICENSE file

##Author

Dmytro Berezanskyi

##Contacts for communication

Telegram [@br0tbent](https://t.me/br0tbent)

##Created with

[Java™ SE Development Kit 15.0.1](https://www.oracle.com/java/technologies/javase/15-relnote-issues.html)

[GitHub](https://github.com/) 

[Spring Boot](https://start.spring.io/)

[Telegram Bots](https://core.telegram.org/bots)

[Gradle](https://gradle.org/)

[Lombok](https://projectlombok.org/)

[Apache Log4j](https://logging.apache.org/log4j/)

[JUnit 4](https://junit.org/junit4/)

A complete list of dependencies and used component versions can be found in the build.gradle.

#Usage

Before building, you need to download and create a [PostgreSQL](https://www.postgresql.org/) database, then change the values (port, tableName, userName, password) in application.properties  to your own.

```
spring.datasource.url=jdbc:postgresql://localhost:"port"/"tableName"?sslmode=disable
spring.datasource.username="userName"
spring.datasource.password="password"
```


Also you need to create a telegram bot using [ BotFather](https://t.me/BotFather), then change bot_token and bot_name in application.properties to your own.

```
bot_name="your bot name"
bot_token="your bot token"
```



The bot isn't hosted on the server, so you need to run the application to start.

[@Br0tBeNtDimaBerTestExchange_bot](https://t.me/Br0tBeNtDimaBerTestExchange_bot)
available on Telegram


