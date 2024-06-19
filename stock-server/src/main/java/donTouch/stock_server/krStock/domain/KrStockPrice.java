package donTouch.stock_server.krStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.stock_server.stock.domain.StockPrice;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "kr_stock_full_prices")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KrStockPrice extends StockPrice {
    Integer krStockId;
}
