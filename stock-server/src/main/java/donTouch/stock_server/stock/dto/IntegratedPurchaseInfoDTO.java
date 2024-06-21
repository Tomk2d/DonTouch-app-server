package donTouch.stock_server.stock.dto;

import donTouch.stock_server.web.dto.PurchaseInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IntegratedPurchaseInfoDTO {
    PurchaseInfoDTO purchaseInfo;
    StockDTO stock;
}
