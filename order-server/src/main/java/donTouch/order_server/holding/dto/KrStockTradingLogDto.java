package donTouch.order_server.holding.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KrStockTradingLogDto {

    private Long userId;
    private String krStockId;
    private Long krHoldingStockId;
    private int krStockBuyPrice;
    private int krStockBuyAmount;
    private int combination;
    private int tradingType;
}
