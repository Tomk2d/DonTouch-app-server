package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.KrStockTradingLog;
import donTouch.order_server.holding.domain.KrStockTradingLogJpaRepository;
import donTouch.order_server.holding.dto.KrStockTradingLogDto;
import donTouch.order_server.utils.KrStockLogMapper;
import donTouch.order_server.utils.KrStockMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class KrStockTradingLogServiceImpl implements KrStockTradingLogService {
    private final KrStockTradingLogJpaRepository krStockTradingLogRepository;
    private final KrStockLogMapper krStockLogMapper = KrStockLogMapper.INSTANCE;

    @Override
    public void save(KrStockTradingLogDto krStockTradingLogDto) {
        KrStockTradingLog entity = krStockLogMapper.toEntity(krStockTradingLogDto);
        krStockTradingLogRepository.save(entity);
    }
}
