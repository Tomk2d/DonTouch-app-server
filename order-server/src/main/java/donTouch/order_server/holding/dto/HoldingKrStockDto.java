package donTouch.order_server.holding.dto;

import donTouch.order_server.kafka.dto.TradingStockInfoDto;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoldingKrStockDto {
    private Long userId;
    private String krStockId;
    private int krStockAmount;

    public TradingStockInfoDto convertToTradingStockInfoDTO() {
        return new TradingStockInfoDto(userId, true, krStockId);
    }
}
