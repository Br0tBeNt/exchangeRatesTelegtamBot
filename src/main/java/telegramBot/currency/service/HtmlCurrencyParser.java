package telegramBot.currency.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import telegramBot.currency.data.ExchangeRate;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j

abstract public class HtmlCurrencyParser implements CurrencyParser {

    private String html;
    private String buyPattern;
    private String salePattern;

    @Override
    public ExchangeRate getRate(String base, String currency) {
        String html = String.format(this.html, currency, base);
        try {
            Document doc = Jsoup.connect(html).get();
            Element buyElement = doc.select(String.format(buyPattern, currency)).first();
            Element saleElement = doc.select(String.format(salePattern, currency)).first();
            double purchaseRate = Double.parseDouble(buyElement.text());
            double saleRate = Double.parseDouble(saleElement.text());

            return ExchangeRate.builder()
                    .baseCurrency(base)
                    .currency(currency)
                    .saleRate(saleRate)
                    .purchaseRate(purchaseRate)
                    .build();
        } catch (IOException e) {

            log.error("Failed to process the page: "+e.getMessage());
        }

        return null;
    }
}
