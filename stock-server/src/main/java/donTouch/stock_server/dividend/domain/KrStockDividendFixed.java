package donTouch.stock_server.dividend.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kr_stock_dividend_fixed")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KrStockDividendFixed extends Dividend {
    @Id
    private Integer id;

    private Integer krStockId;
}
