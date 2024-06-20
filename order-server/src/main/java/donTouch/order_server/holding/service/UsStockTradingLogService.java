package donTouch.order_server.holding.service;


import donTouch.order_server.holding.domain.UsStockTradingLog;
import donTouch.order_server.holding.dto.UsStockTradingLogDto;

public interface UsStockTradingLogService {
    UsStockTradingLog save(UsStockTradingLogDto usStockTradingLogDto);
}
