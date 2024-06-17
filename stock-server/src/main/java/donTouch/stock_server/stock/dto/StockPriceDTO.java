package donTouch.stock_server.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StockPriceDTO {
    LocalDate date;
    Double closePrice;
}
