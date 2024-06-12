package donTouch.stock_server.dividend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "us_stock_dividend_expected")
public class UsStockDividendExpected extends Dividend {
    @Id
    private Integer id;

    private Integer krStockId;
}
