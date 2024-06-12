package donTouch.stock_server.dividend.service;

import donTouch.stock_server.dividend.domain.*;
import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DividendServiceImpl implements DividendService {
    private final KrStockDividendFixedJpaRepository krStockDividendFixedJpaRepository;
    private final KrStockDividendExpectedJpaRepository krStockDividendExpectedJpaRepository;
    private final UsStockDividendFixedJpaRepository usStockDividendFixedJpaRepository;
    private final UsStockDividendExpectedJpaRepository usStockDividendExpectedJpaRepository;

    @Override
    public List<DividendDTO> findCalendar(DividendForm dividendForm) {
        // findByDividendDateBeetweenAndSymbol 등으로 유저 보유종목 필터링하기
        

        List<Dividend> fixedDividendList = new ArrayList<>();
        fixedDividendList.addAll(krStockDividendFixedJpaRepository.findAllByDividendDateBetween(dividendForm.getStartDate(), dividendForm.getEndDate()));
        fixedDividendList.addAll(usStockDividendFixedJpaRepository.findAllByDividendDateBetween(dividendForm.getStartDate(), dividendForm.getEndDate()));

        List<Dividend> expectedDividendList = new ArrayList<>();
        expectedDividendList.addAll(krStockDividendExpectedJpaRepository.findAllByDividendDateBetween(dividendForm.getStartDate(), dividendForm.getEndDate()));
        expectedDividendList.addAll(usStockDividendExpectedJpaRepository.findAllByDividendDateBetween(dividendForm.getStartDate(), dividendForm.getEndDate()));

        List<DividendDTO> dividendDTOList = new ArrayList<>();

        for (Dividend dividend : fixedDividendList) {
            System.out.println(dividend.getSymbol());
            dividendDTOList.add(dividend.convertToDividendDTO(true));
        }

        for (Dividend dividend : expectedDividendList) {
            System.out.println(dividend.getSymbol());
            dividendDTOList.add(dividend.convertToDividendDTO(false));
        }

        return dividendDTOList;
    }
}
