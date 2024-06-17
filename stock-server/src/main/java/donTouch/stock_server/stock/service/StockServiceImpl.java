package donTouch.stock_server.stock.service;

import donTouch.stock_server.krStock.domain.*;
import donTouch.stock_server.stock.domain.Stock;
import donTouch.stock_server.stock.domain.StockPrice;
import donTouch.stock_server.stock.dto.*;
import donTouch.stock_server.usStock.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {
    private final KrStockJpaRepository krStockJpaRepository;
    private final UsStockJpaRepository usStockJpaRepository;

    private final KrStockDetailJpaRepository krStockDetailJpaRepository;
    private final UsStockDetailJpaRepository usStockDetailJpaRepository;

    private final KrStockPriceJpaRepository krStockPriceJpaRepository;
    private final UsStockPriceJpaRepository usStockPriceJpaRepository;

    @Override
    public List<StockDTO> findStocks(FindStocksForm findStocksForm) {
        List<Stock> combinedStockList = getCombinedStockList(findStocksForm.getSearchWord(), findStocksForm.getDividendMonth());

        List<StockDTO> stockDTOList = getStockDTOList(combinedStockList, findStocksForm);

        stockDTOList.sort(Comparator.comparingDouble(StockDTO::getPersonalizedScore).reversed());

        return getPagedStockDTOList(stockDTOList, findStocksForm.getPage(), findStocksForm.getSize());
    }

    @Override
    public Map<String, Object> findStockDetail(FindStockDetailForm findStockDetailForm) throws InstanceNotFoundException {
        Map<String, Object> stockDetail = new LinkedHashMap<>();

        if (findStockDetailForm.getExchange().equals("KSC")) {
            Optional<KrStock> krStock = krStockJpaRepository.findById(findStockDetailForm.getStockId());
            if (krStock.isEmpty()) {
                throw new InstanceNotFoundException();
            }

            Optional<KrStockDetail> krStockDetail = krStockDetailJpaRepository.findByKrStockId(findStockDetailForm.getStockId());
            if (krStockDetail.isEmpty()) {
                throw new InstanceNotFoundException();
            }

            Stock stock = krStock.get();
            stockDetail.put("basic_info", stock.convertToDTO());
            stockDetail.put("detail_info", krStockDetail.get().convertToDTO());

            return stockDetail;
        }

        Optional<UsStock> usStock = usStockJpaRepository.findById(findStockDetailForm.getStockId());
        if (usStock.isEmpty()) {
            throw new InstanceNotFoundException();
        }

        Optional<UsStockDetail> usStockDetail = usStockDetailJpaRepository.findByUsStockId(findStockDetailForm.getStockId());
        if (usStockDetail.isEmpty()) {
            throw new InstanceNotFoundException();
        }

        Stock stock = usStock.get();
        stockDetail.put("basic_info", stock.convertToDTO());
        stockDetail.put("detail_info", usStockDetail.get().convertToDTO());

        return stockDetail;
    }

    @Override
    public Map<String, Object> findStockPrices(FindStockPricesForm findStockPricesForm) throws InstanceNotFoundException {
        Map<String, Object> response = new LinkedHashMap<>();

        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusMonths(findStockPricesForm.getMonth());

        List<StockPrice> stockPriceList;

        if (findStockPricesForm.getExchange().equals("KSC")) {
            stockPriceList = new ArrayList<>(krStockPriceJpaRepository.findAllByKrStockIdAndPriceDateGreaterThanEqual(findStockPricesForm.getStockId(), startDate));
        } else {
            stockPriceList = new ArrayList<>(usStockPriceJpaRepository.findAllByUsStockIdAndPriceDateGreaterThanEqual(findStockPricesForm.getStockId(), startDate));
        }

        if (stockPriceList.isEmpty()) {
            throw new InstanceNotFoundException();
        }

        response.put("exchange", findStockPricesForm.getExchange());
        response.put("stock_id", findStockPricesForm.getStockId());
        response.put("symbol", stockPriceList.get(0).getSymbol());

        stockPriceList.sort(Comparator.comparing(StockPrice::getPriceDate).reversed());
        response.put("close_prices", applyIntervalAndConvertToDTO(stockPriceList, findStockPricesForm.getInterval()));

        return response;
    }

    List<StockPriceDTO> applyIntervalAndConvertToDTO(List<StockPrice> stockPriceList, int interval) {
        List<StockPriceDTO> filteredStockPriceDTOList = new ArrayList<>();
        for (int i = 0; i < stockPriceList.size(); i++) {
            if (i % interval == 0) {
                StockPrice stockPrice = stockPriceList.get(i);
                filteredStockPriceDTOList.add(stockPrice.convertToDTO());
            }
        }
        return filteredStockPriceDTOList;
    }

    List<Stock> getCombinedStockList(String searchWord, Integer dividendMonth) {
        List<Stock> combinedStockList = new ArrayList<>();

        System.out.println("searchWord: " + searchWord + ", dividendMonth: " + dividendMonth);
        if (dividendMonth != null) {
            combinedStockList.addAll(krStockJpaRepository.findAllByDividendMonth(dividendMonth));
            combinedStockList.addAll(usStockJpaRepository.findAllByDividendMonth(dividendMonth));

            if (searchWord != null) {
                return combinedStockList.stream()
                        .filter(stock -> stock.getName().contains(searchWord))
                        .collect(Collectors.toList());
            }
            return combinedStockList;
        }

        if (searchWord != null) {
            combinedStockList.addAll(krStockJpaRepository.findDistinctBySymbolContainingOrNameContaining(searchWord, searchWord));
            combinedStockList.addAll(usStockJpaRepository.findDistinctBySymbolContainingOrNameContaining(searchWord, searchWord));
            return combinedStockList;
        }

        combinedStockList.addAll(krStockJpaRepository.findAll());
        combinedStockList.addAll(usStockJpaRepository.findAll());
        return combinedStockList;
    }

    List<StockDTO> getPagedStockDTOList(List<StockDTO> stockDTOList, int page, int size) {
        int start = size * page;
        int end = start + size;

        if (start >= stockDTOList.size()) {
            return List.of();
        }

        return stockDTOList.subList(start, Math.min(end, stockDTOList.size() - 1));
    }

    List<StockDTO> getStockDTOList(List<Stock> stockList, FindStocksForm findStocksForm) {
        return stockList.stream()
                .map(stock -> stock.convertToDTO(
                        findStocksForm.getSafeScore(), findStocksForm.getGrowthScore(), findStocksForm.getDividendScore()))
                .collect(Collectors.toList());
    }
}
