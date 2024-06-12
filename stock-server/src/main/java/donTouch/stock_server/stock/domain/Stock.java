package donTouch.stock_server.stock.domain;

import donTouch.stock_server.stock.dto.StockDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Stock {
    @Id
    @Column(name = "id")
    int id;
    private String symbol;
    private String name;
    private String type;
    private String exchange;

    Integer dividendMonth;
    Double dividendYieldTtm;

    Double safeScore;
    Double growthScore;
    Double dividendScore;

    LocalDateTime updatedDate;
    
    public StockDTO convertToStockDTO() {
        return new StockDTO(id, symbol, name, type, exchange, dividendMonth, dividendYieldTtm);
    }
}
