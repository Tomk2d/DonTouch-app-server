package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingUsStock;
import donTouch.order_server.holding.domain.HoldingUsStockJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HoldingUsStockServiceImpl implements HoldingUsStockService {
    HoldingUsStockJpaRepository holdingUsStockJpaRepository;

    @Override
    public List<String> findHoldingStockIds(Long userId) {
        List<HoldingUsStock> holdingKrStocks = holdingUsStockJpaRepository.findAllByUserId(userId);

        return holdingKrStocks.stream()
                .map(HoldingUsStock::getUsStockId)
                .collect(Collectors.toList());
    }
}
