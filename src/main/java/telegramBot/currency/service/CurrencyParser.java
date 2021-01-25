package telegramBot.currency.service;

import telegramBot.currency.data.ExchangeRate;
import telegramBot.currency.data.ExchangeRateConstants;

public interface CurrencyParser {

    ExchangeRate getRate(String base, String currency);

    RateProvider getRateProvider();

    enum RateProvider{

        PB(ExchangeRateConstants.PB),
        OB(ExchangeRateConstants.OB),
        HB(ExchangeRateConstants.HB);

        public final String name;

        RateProvider(String name) {
            this.name = name;
        }

        public static RateProvider of(String provider){
            for (RateProvider rp : values()) {
                if (rp.name.equals(provider)) {
                    return rp;
                }
            }
            return null;
        }
    }
}
