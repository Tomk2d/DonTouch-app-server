package donTouch.stock_server.stock.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private String symbol;
    private String name;
    private String type;
    private String exchange;

    int dividendMonth;
    double dividendYieldTtm = 0.0;
}
