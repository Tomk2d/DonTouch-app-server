package donTouch.order_server.holding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PurchasedStockDTO {
    LocalDate date;
    Integer combinationId;

    String nation;
    String symbol;
    Integer price;
    Integer amount;
}
