package donTouch.stock_server.dividend.service;

import donTouch.stock_server.dividend.domain.*;
import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DividendServiceImpl implements DividendService {
    private final KrStockDividendFixedJpaRepository krStockDividendFixedJpaRepository;
    private final KrStockDividendExpectedJpaRepository krStockDividendExpectedJpaRepository;
    private final UsStockDividendFixedJpaRepository usStockDividendFixedJpaRepository;
    private final UsStockDividendExpectedJpaRepository usStockDividendExpectedJpaRepository;

    @Override
    public List<DividendDTO> findCalendar(DividendForm dividendForm) {
        // example holdings
        List<Integer> holdingKrStockIds = List.of(5, 10, 34, 42, 109, 210);
        List<Integer> holdingUsStockIds = List.of(1523, 1586, 579, 1104, 1615);

        Map<String, List<Dividend>> dividendList = getDividendListFilteredByIdAndDateBetween(holdingKrStockIds, holdingUsStockIds, dividendForm.getStartDate(), dividendForm.getEndDate());

        List<Dividend> fixedDividendList = dividendList.get("fixedDividendList");
        List<Dividend> expectedDividendList = dividendList.get("expectedDividendList");

        List<DividendDTO> dividendDTOList = new ArrayList<>();

        dividendDTOList.addAll(convertToDividendDTOList(fixedDividendList));
        dividendDTOList.addAll(convertToDividendDTOList(expectedDividendList));

        return dividendDTOList;
    }

    Map<String, List<Dividend>> getDividendListFilteredByIdAndDateBetween(List<Integer> holdingKrStockIds, List<Integer> holdingUsStockIds, LocalDate startDate, LocalDate endDate) {
        List<Dividend> fixedDividendList = new ArrayList<>();
        fixedDividendList.addAll(krStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndKrStockIdIn(startDate, endDate, holdingKrStockIds));
        fixedDividendList.addAll(usStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndUsStockIdIn(startDate, endDate, holdingUsStockIds));

        List<Dividend> expectedDividendList = new ArrayList<>();
        expectedDividendList.addAll(krStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndKrStockIdIn(startDate, endDate, holdingKrStockIds));
        expectedDividendList.addAll(usStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndUsStockIdIn(startDate, endDate, holdingUsStockIds));
        
        Map<String, List<Dividend>> result = new HashMap<>();
        result.put("fixedDividendList", fixedDividendList);
        result.put("expectedDividendList", expectedDividendList);

        return result;
    }

    List<DividendDTO> convertToDividendDTOList(List<Dividend> dividendList) {
        List<DividendDTO> dividendDTOList = new ArrayList<>();

        for (Dividend dividend : dividendList) {
            dividendDTOList.add(dividend.convertToDividendDTO(true));
        }

        return dividendDTOList;
    }

}
