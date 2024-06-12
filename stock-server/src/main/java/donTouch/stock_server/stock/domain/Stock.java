package donTouch.stock_server.stock.domain;

import donTouch.stock_server.stock.dto.StockDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Stock {
    private String symbol;
    private String name;
    private String type;
    private String exchange;

    Integer dividendMonth;
    Double dividendYieldTtm;

    Double safeScore;
    Double growthScore;
    Double dividendScore;

    public StockDTO convertToStockDTO() {
        return new StockDTO(symbol, name, type, exchange, dividendMonth, dividendYieldTtm);
    }
}
