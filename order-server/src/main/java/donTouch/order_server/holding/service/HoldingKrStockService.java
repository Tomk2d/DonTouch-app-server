package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingKrStock;
import donTouch.order_server.holding.dto.HoldingKrStockDto;
import donTouch.order_server.holding.dto.HoldingKrStockFindForm;

public interface HoldingKrStockService {
    HoldingKrStock save(HoldingKrStockDto holdingKrStockDto);
    HoldingKrStock sellStockUpdate(HoldingKrStockFindForm holdingKrStockFindForm);
    HoldingKrStock findHolding(Long userId, String krStockId);
}
