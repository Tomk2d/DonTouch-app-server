package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.KrStockTradingLog;
import donTouch.order_server.holding.dto.KrStockTradingLogDto;

public interface KrStockTradingLogService {
    void save(KrStockTradingLogDto krStockTradingLogDto);
}
