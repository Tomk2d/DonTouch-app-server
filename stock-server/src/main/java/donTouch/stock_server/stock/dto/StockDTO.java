package donTouch.stock_server.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockDTO {
    private Integer id;
    private String symbol;
    private String name;
    private String type;
    private String exchange;

    Integer dividendMonth;
    Double dividendYieldTtm;
}
