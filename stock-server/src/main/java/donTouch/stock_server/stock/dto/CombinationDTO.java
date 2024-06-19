package donTouch.stock_server.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CombinationDTO {
    Integer stockId;
    String name;
    String symbol;
    Integer price;
    Integer quantity;
    Long dividend;
}
