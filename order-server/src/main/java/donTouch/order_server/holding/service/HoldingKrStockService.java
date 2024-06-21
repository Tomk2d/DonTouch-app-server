package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingKrStock;
import donTouch.order_server.holding.dto.HoldingKrStockDto;
import donTouch.order_server.holding.dto.HoldingKrStockFindForm;
import donTouch.order_server.holding.dto.PurchaseInfoDTO;

import java.util.List;

public interface HoldingKrStockService {
    HoldingKrStock save(HoldingKrStockDto holdingKrStockDto);

    HoldingKrStock sellStockUpdate(HoldingKrStockFindForm holdingKrStockFindForm);

    HoldingKrStock findHolding(Long userId, String krStockId);

    List<String> findHoldingStockIds(Long userId);

    List<PurchaseInfoDTO> findHoldingStockInfos(Long userId, List<String> symbols);
}
