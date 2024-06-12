package donTouch.stock_server.dividend.service;

import donTouch.stock_server.dividend.domain.KrStockDividendExpected;
import donTouch.stock_server.dividend.domain.KrStockDividendExpectedJpaRepository;
import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DividendServiceImpl implements DividendService {
    private final KrStockDividendExpectedJpaRepository krStockDividendExpectedJpaRepository;

    @Override
    public List<DividendDTO> findCalendar(DividendForm dividendForm) {
        List<KrStockDividendExpected> krStockDividendExpectedList = krStockDividendExpectedJpaRepository.findByDividendDateBetween(dividendForm.getStartDate(), dividendForm.getEndDate());

        return List.of();
    }
}
