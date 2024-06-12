package donTouch.stock_server.stock.domain;

import donTouch.stock_server.stock.dto.StockDTO;
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
        return new StockDTO(this.getSymbol(), this.getName(), this.getType(), this.getExchange(), this.getDividendMonth(), this.getDividendYieldTtm());
    }
}
