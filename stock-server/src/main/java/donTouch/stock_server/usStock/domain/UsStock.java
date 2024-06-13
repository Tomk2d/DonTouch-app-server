package donTouch.stock_server.usStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.stock_server.stock.domain.Stock;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "us_stocks")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsStock extends Stock {
}
