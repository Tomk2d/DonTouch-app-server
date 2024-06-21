package donTouch.stock_server.dividend.service;

import donTouch.stock_server.dividend.domain.*;
import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import donTouch.stock_server.krStock.domain.KrStockJpaRepository;
import donTouch.stock_server.usStock.domain.UsStockJpaRepository;
import donTouch.stock_server.web.dto.PurchaseInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<DividendDTO> findCalendar(DividendForm dividendForm, Map<String, List<String>> holdingStockResponse, Map<String, List<PurchaseInfoDTO>> holdingPurchases) {
        List<String> holdingKrStockSymbols = holdingStockResponse.get("krSymbols");
        List<String> holdingUsStockSymbols = holdingStockResponse.get("usSymbols");

        List<PurchaseInfoDTO> krHoldingStocks = holdingPurchases.get("krHoldingStocks");
        List<PurchaseInfoDTO> usHoldingStocks = holdingPurchases.get("usHoldingStocks");

        holdingKrStockSymbols = holdingKrStockSymbols.stream()
                .map(s -> s.endsWith(".KS") ? s : s + ".KS")
                .toList();

        List<DividendDTO> dividendDTOList = new ArrayList<>();

        List<Dividend> dividendList = new ArrayList<>(krStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingKrStockSymbols));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, true, krHoldingStocks));

        dividendList = new ArrayList<>(usStockDividendFixedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingUsStockSymbols));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, true, usHoldingStocks));

        dividendList = new ArrayList<>(krStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingKrStockSymbols));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, false, krHoldingStocks));

        dividendList = new ArrayList<>(usStockDividendExpectedJpaRepository.findAllByDividendDateBetweenAndSymbolIn(dividendForm.getStartDate(), dividendForm.getEndDate(), holdingUsStockSymbols));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, false, usHoldingStocks));

        return dividendDTOList;
    }

    List<DividendDTO> convertToDividendDTOList(List<Dividend> dividendList, boolean isFixed, List<PurchaseInfoDTO> holdingPurchases) {
        List<DividendDTO> dividendDTOList = new ArrayList<>();

        for (Dividend dividend : dividendList) {
            Optional<PurchaseInfoDTO> foundPurchase = holdingPurchases.stream()
                    .filter(stock -> (stock.getSymbol() + ".KS").equals(dividend.getSymbol()))
                    .findFirst();

            if (foundPurchase.isPresent()) {
                DividendDTO dividendDTO = dividend.convertToDividendDTO(isFixed, foundPurchase.get().getQuantity());
                dividendDTOList.add(dividendDTO);
            }
        }
        return dividendDTOList;
    }
}
