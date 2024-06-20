package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.UsStockDividendLog;
import donTouch.order_server.holding.dto.UsStockDividendLogDto;

public interface UsStockDividendLogService {
    UsStockDividendLog save(UsStockDividendLogDto usStockDividendLogDto);
}
