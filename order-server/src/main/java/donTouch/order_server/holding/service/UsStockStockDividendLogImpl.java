package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.UsStockDividendLogJpaRepository;
import donTouch.order_server.holding.domain.UsStockDividendLog;
import donTouch.order_server.holding.dto.UsStockDividendLogDto;
import donTouch.order_server.utils.UsStockDividendLogMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class UsStockStockDividendLogImpl implements UsStockDividendLogService {
    private final UsStockDividendLogJpaRepository usStockDividendLogJpaRepository;
    private final UsStockDividendLogMapper usStockDividendLogMapper = UsStockDividendLogMapper.INSTANCE;

    @Override
    public UsStockDividendLog save(UsStockDividendLogDto usStockDividendLogDto) {
        UsStockDividendLog entity = usStockDividendLogMapper.toEntity(usStockDividendLogDto);
        UsStockDividendLog savedEntity = usStockDividendLogJpaRepository.save(entity);
        return savedEntity;
    }
}
