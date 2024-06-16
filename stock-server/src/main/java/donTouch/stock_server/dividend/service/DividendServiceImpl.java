package donTouch.stock_server.dividend.service;

import donTouch.stock_server.dividend.domain.*;
import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import donTouch.stock_server.krStock.domain.KrStockJpaRepository;
import donTouch.stock_server.stock.domain.Stock;
import donTouch.stock_server.usStock.domain.UsStockJpaRepository;
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

    private final KrStockJpaRepository krStockJpaRepository;
    private final UsStockJpaRepository usStockJpaRepository;

    @Override
    public List<DividendDTO> findCalendar(DividendForm dividendForm) {
        // example holdings
        List<Integer> holdingKrStockIds = List.of(5, 10, 34, 42, 109, 210);
        List<Integer> holdingUsStockIds = List.of(1523, 1586, 579, 1104, 1615);

        List<DividendDTO> dividendDTOList = new ArrayList<>();

        List<Dividend> dividendList = new ArrayList<>(krStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndKrStockIdIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingKrStockIds));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, true));

        dividendList = new ArrayList<>(usStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndUsStockIdIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingUsStockIds));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, true));

        dividendList = new ArrayList<>(krStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndKrStockIdIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingKrStockIds));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, false));

        dividendList = new ArrayList<>(usStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndUsStockIdIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingKrStockIds));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, false));

        return dividendDTOList;
    }

    <T extends Stock> List<DividendDTO> convertToDividendDTOList(List<Dividend> dividendList, boolean isFixed) {
        List<DividendDTO> dividendDTOList = new ArrayList<>();

        for (Dividend dividend : dividendList) {
            dividendDTOList.add(dividend.convertToDividendDTO(isFixed));
        }
        return dividendDTOList;
    }
}
