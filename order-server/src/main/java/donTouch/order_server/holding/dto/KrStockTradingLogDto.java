package donTouch.order_server.holding.dto;

import java.sql.Date;
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
public class KrStockTradingLogDto {

    private Long userId;
    private String krStockId;
    private Long krHoldingStockId;
    private int krStockBuyPrice;
    private int krStockBuyAmount;
    private int combination;
    private int tradingType;
}
