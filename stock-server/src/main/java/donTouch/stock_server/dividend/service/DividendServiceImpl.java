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
import java.util.Map;

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
    public List<DividendDTO> findCalendar(DividendForm dividendForm, Map<String, List<String>> holdingStockResponse) {
        List<String> holdingKrStockSymbols = holdingStockResponse.get("krSymbols");
        List<String> holdingUsStockSymbols = holdingStockResponse.get("usSymbols");

        holdingKrStockSymbols = holdingKrStockSymbols.stream()
                .map(s -> s.endsWith(".KS") ? s : s + ".KS")
                .toList();

        List<DividendDTO> dividendDTOList = new ArrayList<>();

        List<Dividend> dividendList = new ArrayList<>(krStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingKrStockSymbols));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, true));

        dividendList = new ArrayList<>(usStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingUsStockSymbols));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, true));

        dividendList = new ArrayList<>(krStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingKrStockSymbols));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, false));

        dividendList = new ArrayList<>(usStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingUsStockSymbols));
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
