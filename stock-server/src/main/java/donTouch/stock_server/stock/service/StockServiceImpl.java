package donTouch.stock_server.stock.service;

import donTouch.stock_server.krStock.domain.*;
import donTouch.stock_server.stock.domain.*;
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

    @Override
    public Map<String, Object> findCombination(FindCombinationForm findCombinationForm) {
        // 2차원 배열 fixed
        List<List<StockDTO>> fixedStockList = getFixedCombinations(findCombinationForm);
        List<List<Combination>> distirbutedStockList = distributeStock(fixedStockList, findCombinationForm.getInvestmentAmount());

        return convertToMap(distirbutedStockList);
    }

    List<List<Combination>> distributeStock(List<List<StockDTO>> fixedStockList, Long investmentAmount) {
        List<List<Combination>> combinationDTOList = convertToCombinationDTO(fixedStockList);
        PriorityQueue<Combination> queueSortedByPrice = getQueueSortedByPrice(combinationDTOList);
        long boughtStockPrice = 0;
        long[] dividend = {-1, 0, 0, 0};

        while (!queueSortedByPrice.isEmpty()) {
            Combination combination = queueSortedByPrice.poll();

            if (dividend[combination.getStock().getDividendMonth()] > 0) {
                continue;
            }

            if (boughtStockPrice + combination.getPrice() > investmentAmount) {
                break;
            }

            combination.addQuantity();
            dividend[combination.getStock().getDividendMonth()] += combination.getDividend();

            boughtStockPrice += combination.getPrice();
        }

        PriorityQueue<MonthDividend> queueSortedByDividend = new PriorityQueue<>(new Comparator<MonthDividend>() {
            @Override
            public int compare(MonthDividend o1, MonthDividend o2) {
                return Long.compare(o1.getDividend(), o2.getDividend());
            }
        });
        queueSortedByDividend.add(new MonthDividend(1, dividend[1]));
        queueSortedByDividend.add(new MonthDividend(2, dividend[2]));
        queueSortedByDividend.add(new MonthDividend(3, dividend[3]));

        while (true) {
            MonthDividend lowestDividendMonth = queueSortedByDividend.poll();
            List<Combination> combinationListToBuy = combinationDTOList.get(lowestDividendMonth.getMonth() - 1);
            Combination combinationToBuy = combinationListToBuy.get(0);

            if (combinationListToBuy.size() == 1 && boughtStockPrice + combinationToBuy.getPrice() > investmentAmount) {
                queueSortedByDividend.add(lowestDividendMonth);
                break;
            }

            if (combinationListToBuy.size() >= 2) {
                long amount0 = combinationListToBuy.get(0).getAmount();
                long amount1 = combinationListToBuy.get(1).getAmount();

                if (amount0 > amount1) {
                    combinationToBuy = combinationListToBuy.get(1);
                }
            }

            boughtStockPrice += combinationToBuy.getPrice();
            combinationToBuy.addQuantity();

            lowestDividendMonth.addDividend(combinationToBuy.getPrice());
            queueSortedByDividend.add(lowestDividendMonth);
        }

        return combinationDTOList;
    }

    PriorityQueue<Combination> getQueueSortedByPrice(List<List<Combination>> combinationDTOList) {
        PriorityQueue<Combination> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getPrice(), o2.getPrice()));

        for (List<Combination> combinations : combinationDTOList) {
            pq.addAll(combinations);
        }

        return pq;
    }

    List<List<Combination>> convertToCombinationDTO(List<List<StockDTO>> fixedStockList) {
        List<List<Combination>> combinationDTOList = new ArrayList<>();

        for (List<StockDTO> stockDTOList : fixedStockList) {
            List<Combination> combination = new ArrayList<>();

            for (StockDTO stockDTO : stockDTOList) {
                combination.add(new Combination(stockDTO, getPrice(stockDTO.getExchange(), stockDTO.getId()), 0));
            }
            combinationDTOList.add(combination);
        }
        return combinationDTOList;
    }

    int getPrice(String exchange, Integer id) {
        // 달러 변환까지 해야 함. 현재 id로 랜덤(?)
        return id % 10 * 10000;
    }

    Map<String, Object> convertToMap(List<List<Combination>> distirbutedStockList) {
        Map<String, Object> response = new LinkedHashMap<>();
        int group = 1;
        long totalDividend = 0;

        for (List<Combination> combinationList : distirbutedStockList) {
            List<CombinationDTO> combinationDTOList = new ArrayList<>();

            for (Combination combination : combinationList) {
                if (combination.getQuantity() > 0) {
                    combinationDTOList.add(combination.convertToDTO());
                    totalDividend += combination.getDividend();
                }
            }
            response.put("combination" + group++, combinationDTOList);
        }

        response.put("totalDividend", totalDividend);
        return response;
    }

    List<List<StockDTO>> getFixedCombinations(FindCombinationForm findCombinationForm) {
        List<List<StockDTO>> fixedStockList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            fixedStockList.add(findStocks(new FindStocksForm(null, i + 1, findCombinationForm.getSafeScore(), findCombinationForm.getGrowthScore(), findCombinationForm.getDividendScore(), 0, 2)));
        }

        double minScore = fixedStockList.get(0).get(0).getPersonalizedScore();
        for (int i = 1; i < fixedStockList.size(); i++) {
            if (minScore > fixedStockList.get(i).get(0).getPersonalizedScore()) {
                minScore = fixedStockList.get(i).get(0).getPersonalizedScore();
            }
        }

        for (List<StockDTO> stockDTOS : fixedStockList) {
            if (stockDTOS.get(1).getPersonalizedScore() < minScore) {
                stockDTOS.remove(1);
            }
        }
        return fixedStockList;
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

        if (dividendMonth != null) {
            combinedStockList.addAll(krStockJpaRepository.findAllByDividendMonth(dividendMonth));
            combinedStockList.addAll(usStockJpaRepository.findAllByDividendMonth(dividendMonth));

            if (searchWord != null) {
                return combinedStockList.stream()
                        .filter(stock -> stock.getName().toLowerCase().contains(searchWord.toLowerCase())
                                || stock.getSymbol().toLowerCase().contains(searchWord.toLowerCase())
                                || stock.getEnglishName().toLowerCase().contains(searchWord.toLowerCase()))
                        .collect(Collectors.toList());
            }
            return combinedStockList;
        }

        if (searchWord != null) {
            combinedStockList.addAll(krStockJpaRepository.findDistinctBySymbolContainingOrNameContainingOrEnglishNameContaining(searchWord, searchWord, searchWord));
            combinedStockList.addAll(usStockJpaRepository.findDistinctBySymbolContainingOrNameContainingOrEnglishNameContaining(searchWord, searchWord, searchWord));
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
