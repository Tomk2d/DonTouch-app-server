package donTouch.stock_server.krStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.stock_server.stock.domain.Stock;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "kr_stocks")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KrStock extends Stock {
    String corpCode;
}
