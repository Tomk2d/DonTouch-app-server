package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.UsStockTradingLog;
import donTouch.order_server.holding.domain.UsStockTradingLogJpaRepository;
import donTouch.order_server.holding.dto.UsStockTradingLogDto;
import donTouch.order_server.utils.UsStockLogMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class UsStockTradingLogServiceImpl implements UsStockTradingLogService {
    private final UsStockTradingLogJpaRepository usStockTradingLogRepository;
    private final UsStockLogMapper usStockLogMapper = UsStockLogMapper.INSTANCE;

    @Override
    public UsStockTradingLog save(UsStockTradingLogDto usStockTradingLogDto) {
        UsStockTradingLog entity = usStockLogMapper.toEntity(usStockTradingLogDto);
        UsStockTradingLog savedEntity = usStockTradingLogRepository.save(entity);
        return savedEntity;
    }
}
