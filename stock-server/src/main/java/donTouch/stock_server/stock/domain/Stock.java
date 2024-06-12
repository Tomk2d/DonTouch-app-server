package donTouch.stock_server.stock.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Stock extends StockDTO {
    double safeScore;
    double growthScore;
    double dividendScore;

    public StockDTO convertToStockDTO() {
        return new StockDTO(symbol, name, type, exchange, dividendMonth, dividendYieldTtm);
    }
}
