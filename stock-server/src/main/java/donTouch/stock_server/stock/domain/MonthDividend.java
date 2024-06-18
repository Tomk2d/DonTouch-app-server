package donTouch.stock_server.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthDividend {
    private Integer month;
    private Long dividend;

    public void addDividend(Integer dividendToAdd) {
        this.dividend += (long) dividendToAdd;
    }
}
