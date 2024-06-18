package donTouch.stock_server.usStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.stock_server.stock.domain.StockPrice;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "us_stock_full_prices")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsStockPrice extends StockPrice {
    Integer usStockId;
}
