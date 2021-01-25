package telegramBot.currency.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telegramBot.currency.service.CurrencyParser;
import telegramBot.currency.service.CurrencyParser.RateProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CurrencyParserFactory {

    private Map<RateProvider, CurrencyParser> parsers;

    @Autowired
    public CurrencyParserFactory(Set<CurrencyParser> currencyParsers) {
        createParsers(currencyParsers);
    }

    public CurrencyParser findProvider(RateProvider rateProvider) {
        return parsers.get(rateProvider);
    }

    private void createParsers(Set<CurrencyParser> currencyParsers) {
        parsers = new HashMap<>();
        currencyParsers.forEach(parser -> parsers.put(parser.getRateProvider(), parser));
    }

}
