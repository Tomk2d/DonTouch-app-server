package donTouch.order_server.holding.dto;

import donTouch.order_server.kafka.dto.TradingStockInfoDto;
import lombok.*;

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

    public TradingStockInfoDto convertToTradingStockInfoDTO() {
        return new TradingStockInfoDto(userId, false, usStockId);
    }
}
