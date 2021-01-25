package telegramBot.currency.service.impl;


import org.springframework.stereotype.Component;
import telegramBot.currency.service.HtmlCurrencyParser;

@Component
public class PrivatBankParser extends HtmlCurrencyParser {

    public PrivatBankParser() {
        super("https://privatbank.ua/", "td[id=%s_buy]", "td[id=%s_sell]");
    }

    @Override
    public RateProvider getRateProvider() {
        return RateProvider.PB;
    }
}
