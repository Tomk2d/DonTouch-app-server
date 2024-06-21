package donTouch.utils.exchangeRate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeRate {
    USD("USD", 1391.0, 1391.0);

    private String currency;
    private Double buying;
    private Double selling;
}
