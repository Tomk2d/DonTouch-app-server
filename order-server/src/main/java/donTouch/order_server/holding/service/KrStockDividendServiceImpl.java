package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.KrStockDividendLog;
import donTouch.order_server.holding.domain.KrStockDividendLogJpaRepository;
import donTouch.order_server.holding.dto.KrStockDividendLogDto;
import donTouch.order_server.utils.KrStockDividendLogMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class KrStockDividendServiceImpl implements KrStockDividendService {
    private final KrStockDividendLogJpaRepository krStockDividendLogRepository;
    private final KrStockDividendLogMapper krStockDividendLogMapper = KrStockDividendLogMapper.INSTANCE;

    @Override
    public KrStockDividendLog save(KrStockDividendLogDto krStockDividendLogDto) {
        KrStockDividendLog entity = krStockDividendLogMapper.toEntity(krStockDividendLogDto);
        KrStockDividendLog savedEntity = krStockDividendLogRepository.save(entity);
        System.out.println(savedEntity.toString());
        return savedEntity;
    }

    @Override
    public KrStockDividendLogDto findByUserId(Long userId) {
        return null;
    }
}
