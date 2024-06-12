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
    String symbol;
    String name;
    String type;
    String exchange;

    int dividendMonth;
    double dividendYieldTtm = 0.0;
}
