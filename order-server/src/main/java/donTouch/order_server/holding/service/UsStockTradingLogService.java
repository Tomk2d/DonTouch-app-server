package donTouch.order_server.holding.service;


import donTouch.order_server.holding.domain.UsStockTradingLog;
import donTouch.order_server.holding.dto.PurchasedStockDTO;
import donTouch.order_server.holding.dto.UsStockTradingLogDto;

import java.util.List;

public interface UsStockTradingLogService {
    UsStockTradingLog save(UsStockTradingLogDto usStockTradingLogDto);

    List<PurchasedStockDTO> getPurchasedCombinationStocks(Long userId);
}
