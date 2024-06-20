package donTouch.order_server.holding.service;

import java.util.List;

public interface HoldingUsStockService {
    List<String> findHoldingStockIds(Long userId);
}
