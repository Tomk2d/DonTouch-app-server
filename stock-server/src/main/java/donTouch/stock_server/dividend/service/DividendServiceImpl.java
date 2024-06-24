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
    public List<DividendDTO> findCalendar(DividendForm dividendForm, Map<String, List<PurchaseInfoDTO>> holdingPurchases) {
        List<PurchaseInfoDTO> krHoldingStocks = holdingPurchases.get("krHoldingStocks");
        List<PurchaseInfoDTO> usHoldingStocks = holdingPurchases.get("usHoldingStocks");

        List<String> holdingKrStockSymbols = new ArrayList<>();
        for (PurchaseInfoDTO purchaseInfoDTO : krHoldingStocks) {
            String symbol = purchaseInfoDTO.getSymbol();
            if (!symbol.endsWith(".KS")) {
                symbol += ".KS";
            }
            holdingKrStockSymbols.add(purchaseInfoDTO.getSymbol() + ".KS");
        }

        List<String> holdingUsStockSymbols = new ArrayList<>();
        for (PurchaseInfoDTO purchaseInfoDTO : usHoldingStocks) {
            holdingUsStockSymbols.add(purchaseInfoDTO.getSymbol());
        }

        List<Dividend> dividendList = new ArrayList<>(krStockDividendFixedJpaRepository.findAllBySymbolInAndDividendDateBetween(holdingKrStockSymbols, dividendForm.getStartDate(), dividendForm.getEndDate()));
        List<DividendDTO> dividendDTOList = new ArrayList<>(convertToDividendDTOList(dividendList, true, krHoldingStocks, true));

        dividendList = new ArrayList<>(usStockDividendFixedJpaRepository.findAllBySymbolInAndDividendDateBetween(holdingUsStockSymbols, dividendForm.getStartDate(), dividendForm.getEndDate()));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, true, usHoldingStocks, false));

        dividendList = new ArrayList<>(krStockDividendExpectedJpaRepository.findAllBySymbolInAndDividendDateBetween(holdingKrStockSymbols, dividendForm.getStartDate(), dividendForm.getEndDate()));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, false, krHoldingStocks, true));

        dividendList = new ArrayList<>(usStockDividendExpectedJpaRepository.findAllBySymbolInAndDividendDateBetween(holdingUsStockSymbols, dividendForm.getStartDate(), dividendForm.getEndDate()));
        dividendDTOList.addAll(convertToDividendDTOList(dividendList, false, usHoldingStocks, false));

        return dividendDTOList;
    }

    List<DividendDTO> convertToDividendDTOList(List<Dividend> dividendList, boolean isFixed, List<PurchaseInfoDTO> holdingPurchases, Boolean isKr) {
        List<DividendDTO> dividendDTOList = new ArrayList<>();

        for (Dividend dividend : dividendList) {
            Optional<PurchaseInfoDTO> foundPurchase = holdingPurchases.stream()
                    .filter(stock -> (isKr ? stock.getSymbol() + ".KS" : stock.getSymbol()).equals(dividend.getSymbol()))
                    .findFirst();

            if (foundPurchase.isPresent()) {
                DividendDTO dividendDTO = dividend.convertToDividendDTO(isFixed, foundPurchase.get().getQuantity());
                dividendDTOList.add(dividendDTO);
            }
        }
        return dividendDTOList;
    }
}
