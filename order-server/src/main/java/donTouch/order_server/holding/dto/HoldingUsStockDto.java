package donTouch.order_server.holding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoldingUsStockDto {
    private Long userId;
    private String usStockId;
    private int usStockAmount;
}
