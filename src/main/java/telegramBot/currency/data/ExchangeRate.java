package telegramBot.currency.data;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    private String baseCurrency;
    private String currency;
    private double saleRate;
    private double purchaseRate;

    @Override
    public String toString() {
        return "Покупка: " +
                purchaseRate +
                "\n" +
                "Продажа: " +
                saleRate;
    }
}


