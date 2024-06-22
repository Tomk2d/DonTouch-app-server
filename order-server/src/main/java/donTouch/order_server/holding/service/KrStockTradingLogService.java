package donTouch.order_server.holding.service;

import donTouch.order_server.holding.dto.KrStockTradingLogDto;
import donTouch.order_server.holding.dto.PurchasedStockDTO;

import java.util.List;

public interface KrStockTradingLogService {
    void save(KrStockTradingLogDto krStockTradingLogDto);

    List<PurchasedStockDTO> getPurchasedCombinationStocks(Long userId);
}
