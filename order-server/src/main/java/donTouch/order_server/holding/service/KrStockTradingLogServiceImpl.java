package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.KrStockTradingLog;
import donTouch.order_server.holding.domain.KrStockTradingLogJpaRepository;
import donTouch.order_server.holding.dto.KrStockTradingLogDto;
import donTouch.order_server.holding.dto.PurchasedStockDTO;
import donTouch.order_server.utils.KrStockLogMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class KrStockTradingLogServiceImpl implements KrStockTradingLogService {
    private final KrStockTradingLogJpaRepository krStockTradingLogRepository;
    private final KrStockLogMapper krStockLogMapper = KrStockLogMapper.INSTANCE;

    @Override
    public void save(KrStockTradingLogDto krStockTradingLogDto) {
        KrStockTradingLog entity = krStockLogMapper.toEntity(krStockTradingLogDto);
        log.info(entity.toString());
        krStockTradingLogRepository.save(entity);
    }

    @Override
    public List<PurchasedStockDTO> getPurchasedCombinationStocks(Long userId) {
        List<KrStockTradingLog> krStockTradingLogList = krStockTradingLogRepository.findAllByUserIdAndTradingTypeAndCombinationGreaterThan(userId, 1, 99999999);

        return krStockTradingLogList.stream()
                .map(KrStockTradingLog::convertToPurchasedStockDTO)
                .collect(Collectors.toList());
    }
}
