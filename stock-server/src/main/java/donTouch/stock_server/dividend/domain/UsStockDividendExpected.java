package donTouch.stock_server.dividend.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "us_stock_dividend_expected")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsStockDividendExpected extends Dividend {
}
