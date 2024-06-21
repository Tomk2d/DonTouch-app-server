package donTouch.stock_server.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PurchasedStockDTO {
    LocalDateTime date;
    Integer combinationId;

    String nation;
    String symbol;
    Integer price;
    Integer amount;
}
