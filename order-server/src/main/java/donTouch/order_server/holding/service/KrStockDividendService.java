package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.KrStockDividendLog;
import donTouch.order_server.holding.dto.KrStockDividendLogDto;

public interface KrStockDividendService {
    public KrStockDividendLog save(KrStockDividendLogDto krStockDividendLogDto);
    public KrStockDividendLogDto findByUserId(Long userId);
}
