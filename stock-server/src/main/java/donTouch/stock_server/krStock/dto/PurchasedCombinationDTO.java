package donTouch.stock_server.krStock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchasedCombinationDTO {
    Integer stockId;
    String name;
    String symbol;
    Integer quantity;
}
