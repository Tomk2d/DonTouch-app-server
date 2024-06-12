package donTouch.stock_server.dividend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kr_stock_dividend_fixed")
public class UsStockDividendFixed extends Dividend {
    @Id
    private Integer id;

    private Integer krStockId;
}
