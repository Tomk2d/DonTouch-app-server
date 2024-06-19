package donTouch.utils.exchangeRate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeRate {
    USD("USD", 1363.92, 1391.47);

    private String currency;
    private Double buying;
    private Double selling;
}
