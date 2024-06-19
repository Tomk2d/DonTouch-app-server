package donTouch.stock_server.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRateDTO {
    private String currency;
    private Double buying;
    private Double selling;
}
