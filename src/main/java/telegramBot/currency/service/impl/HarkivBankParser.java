package telegramBot.currency.service.impl;


import org.springframework.stereotype.Component;
import telegramBot.currency.service.HtmlCurrencyParser;

@Component
public class HarkivBankParser extends HtmlCurrencyParser {

    public HarkivBankParser() {
        super("https://money24.kharkov.ua/%s-%s/", "span[class=js-currency-rate-buy hidden]", "span[class=js-currency-rate-sale hidden]");
    }

    @Override
    public RateProvider getRateProvider() {
        return RateProvider.HB;
    }
}
