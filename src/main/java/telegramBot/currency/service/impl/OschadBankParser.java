package telegramBot.currency.service.impl;


import org.springframework.stereotype.Component;
import telegramBot.currency.service.HtmlCurrencyParser;

@Component
public class OschadBankParser extends HtmlCurrencyParser {

    public OschadBankParser() {
        super("https://www.oschadbank.ua/ua", "strong[class=buy-%s]", "strong[class=sell-%s]");
    }

    @Override
    public RateProvider getRateProvider() {
        return RateProvider.OB;
    }
}
