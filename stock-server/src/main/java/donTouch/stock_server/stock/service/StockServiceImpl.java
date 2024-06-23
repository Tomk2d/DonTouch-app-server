package donTouch.stock_server.stock.service;

import donTouch.stock_server.kafka.dto.ChangeScoreDto;
import donTouch.stock_server.kafka.dto.TradingStockInfoDto;
import donTouch.stock_server.krStock.domain.*;
import donTouch.stock_server.krStock.dto.PurchasedCombinationDTO;
import donTouch.stock_server.stock.domain.Combination;
import donTouch.stock_server.stock.domain.MonthDividend;
import donTouch.stock_server.stock.domain.Stock;
import donTouch.stock_server.stock.domain.StockPrice;
import donTouch.stock_server.stock.dto.*;
import donTouch.stock_server.usStock.domain.*;
import donTouch.stock_server.web.dto.LikeStockDTO;
import donTouch.stock_server.web.dto.PurchaseInfoDTO;
import donTouch.stock_server.web.dto.PurchasedStockDTO;
import donTouch.utils.exchangeRate.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {
    private final KrStockJpaRepository krStockJpaRepository;
    private final UsStockJpaRepository usStockJpaRepository;

    private final KrStockDetailJpaRepository krStockDetailJpaRepository;
    private final UsStockDetailJpaRepository usStockDetailJpaRepository;

    private final KrStockPriceJpaRepository krStockPriceJpaRepository;
    private final UsStockPriceJpaRepository usStockPriceJpaRepository;

    private final KrLatestCloseJpaRepository krLatestCloseJpaRepository;
    private final UsLatestCloseJpaRepository usLatestCloseJpaRepository;

    @Override
    public List<StockDTO> findStocks(FindStocksForm findStocksForm) {
        List<Stock> combinedStockList = getCombinedStockList(findStocksForm.getSearchWord(), findStocksForm.getDividendMonth());

        List<StockDTO> stockDTOList = getStockDTOList(combinedStockList, findStocksForm);

        stockDTOList.sort(Comparator.comparingDouble(StockDTO::getPersonalizedScore).reversed());

        return getPagedList(stockDTOList, findStocksForm.getPage(), findStocksForm.getSize());
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
            stockPriceList = new ArrayList<>(krStockPriceJpaRepository.findAllByKrStockIdAndDateGreaterThanEqual(findStockPricesForm.getStockId(), startDate));
        } else {
            stockPriceList = new ArrayList<>(usStockPriceJpaRepository.findAllByUsStockIdAndDateGreaterThanEqual(findStockPricesForm.getStockId(), startDate));
        }

        if (stockPriceList.isEmpty()) {
            throw new InstanceNotFoundException();
        }

        response.put("exchange", findStockPricesForm.getExchange());
        response.put("stock_id", findStockPricesForm.getStockId());
        response.put("symbol", stockPriceList.get(0).getSymbol());

        stockPriceList.sort(Comparator.comparing(StockPrice::getDate).reversed());
        response.put("prices", applyIntervalAndConvertToDTO(stockPriceList, findStockPricesForm.getInterval()));

        return response;
    }

    @Override
    public Map<String, Object> findCombination(FindCombinationForm findCombinationForm) {
        List<List<StockDTO>> fixedStockList = getFixedCombinations(findCombinationForm);

        List<List<Combination>> distirbutedStockList = distributeStock(fixedStockList, findCombinationForm.getInvestmentAmount());

        return convertToMap(distirbutedStockList);
    }

    @Override
    public Map<String, Object> distributeCombination(DistributeCombinationForm distributeCombinationForm) {
        if (countStocks(distributeCombinationForm) == 0) {
            throw new IllegalStateException();
        }

        List<List<StockDTO>> fixedStockList = convertToFixedStockList(distributeCombinationForm);
        List<List<Combination>> distirbutedStockList = distributeStock(fixedStockList, distributeCombinationForm.getInvestmentAmount());

        return convertToMap(distirbutedStockList);
    }

    @Override
    public Map<String, Object> findLikeStocks(List<LikeStockDTO> likeStockDTOList) {
        Map<String, Object> response = new LinkedHashMap<>();
        List<StockDTO> krStockDTOList = new ArrayList<>();
        List<StockDTO> usStockDTOList = new ArrayList<>();

        for (LikeStockDTO likeStockDTO : likeStockDTOList) {
            if (likeStockDTO.getExchange().equals("KSC")) {
                Optional<KrStock> KrStock = krStockJpaRepository.findById(likeStockDTO.getStockId());

                if (KrStock.isPresent()) {
                    Stock stock = KrStock.get();
                    krStockDTOList.add(stock.convertToDTO());
                }
                continue;
            }

            Optional<UsStock> usStock = usStockJpaRepository.findById(likeStockDTO.getStockId());
            if (usStock.isPresent()) {
                Stock stock = usStock.get();
                usStockDTOList.add(stock.convertToDTO());
            }
        }

        response.put("krLikeStocks", krStockDTOList);
        response.put("usLikeStocks", usStockDTOList);

        return response;
    }

    @Override
    public Map<String, Object> findHoldingStocks(Map<String, List<PurchaseInfoDTO>> holdingStockDTOList) {
        List<PurchaseInfoDTO> krHoldingStocks = holdingStockDTOList.get("krHoldingStocks");
        List<PurchaseInfoDTO> usHoldingStocks = holdingStockDTOList.get("usHoldingStocks");
        long krTotalPurchase = 0;
        long usTotalPurchase = 0;

        List<IntegratedPurchaseInfoDTO> krHolding = new ArrayList<>();
        for (PurchaseInfoDTO purchaseInfoDTO : krHoldingStocks) {
            Optional<KrStock> krStock = krStockJpaRepository.findBySymbol(purchaseInfoDTO.getSymbol());

            if (krStock.isPresent()) {
                Stock stock = krStock.get();
                krHolding.add(new IntegratedPurchaseInfoDTO(purchaseInfoDTO, stock.convertToDTO()));
                krTotalPurchase += purchaseInfoDTO.getTotalPurchasePrice();
            }
        }

        List<IntegratedPurchaseInfoDTO> usHolding = new ArrayList<>();
        for (PurchaseInfoDTO purchaseInfoDTO : usHoldingStocks) {
            Optional<UsStock> usStock = usStockJpaRepository.findBySymbol(purchaseInfoDTO.getSymbol());

            if (usStock.isPresent()) {
                Stock stock = usStock.get();
                usHolding.add(new IntegratedPurchaseInfoDTO(purchaseInfoDTO, stock.convertToDTO()));
                usTotalPurchase += purchaseInfoDTO.getTotalPurchasePrice();
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("krHoldingStocks", krHolding);
        response.put("krTotalPurchase", krTotalPurchase);

        response.put("usHoldingStocks", usHolding);
        response.put("usTotalPurchase", usTotalPurchase);

        response.put("totalPurchase", krTotalPurchase + usTotalPurchase);

        return response;
    }

    @Override
    public List<Map<String, Object>> findCombinationInfos(List<PurchasedStockDTO> purchasedStockDTOList, Integer page, Integer size) {
        Map<Integer, List<PurchasedStockDTO>> purchasedStockDTOMap = new HashMap<>();
        Set<String> krSymbols = new HashSet<>();
        Set<String> usSymbols = new HashSet<>();

        for (PurchasedStockDTO purchasedStockDTO : purchasedStockDTOList) {
            purchasedStockDTOMap.putIfAbsent(purchasedStockDTO.getCombinationId(), new ArrayList<>());
            purchasedStockDTOMap.get(purchasedStockDTO.getCombinationId()).add(purchasedStockDTO);

            if (purchasedStockDTO.getNation().equals("kr")) {
                krSymbols.add(purchasedStockDTO.getSymbol());
                continue;
            }
            usSymbols.add(purchasedStockDTO.getSymbol());
        }

        List<KrStock> krStockList = krStockJpaRepository.findAllBySymbolIn(new ArrayList<>(krSymbols));
        List<UsStock> usStockList = usStockJpaRepository.findALlBySymbolIn(new ArrayList<>(usSymbols));

        Map<String, Stock> stocks = new LinkedHashMap<>();
        for (KrStock krStock : krStockList) {
            stocks.putIfAbsent(krStock.getSymbol(), krStock);
        }
        for (UsStock usStock : usStockList) {
            stocks.putIfAbsent(usStock.getSymbol(), usStock);
        }

        List<List<PurchasedStockDTO>> purchasedCombinations = new ArrayList<>(purchasedStockDTOMap.values());
        purchasedCombinations.sort(Comparator.comparing((List<PurchasedStockDTO> list) -> list.get(0).getDate()).reversed());

        List<Map<String, Object>> response = new ArrayList<>();
        for (List<PurchasedStockDTO> purchasedCombination : getPagedList(purchasedCombinations, page, size)) {
            response.add(convertToCombinationMap(purchasedCombination, stocks));
        }
        return response;
    }

    @Override
    public ChangeScoreDto requestToChangeUserScore(TradingStockInfoDto tradingStockInfoDto) {
        Stock stock = null;
        if (tradingStockInfoDto.getIsKr()) {
            Optional<KrStock> krStock = krStockJpaRepository.findBySymbol(tradingStockInfoDto.getSymbol());
            if (krStock.isPresent()) {
                stock = krStock.get();
            }
        } else {
            Optional<UsStock> usStock = usStockJpaRepository.findBySymbol(tradingStockInfoDto.getSymbol());
            if (usStock.isPresent()) {
                stock = usStock.get();
            }
        }

        if (stock == null) {
            log.error("Stock not found");
            return null;
        }

        List<Map.Entry<String, Double>> list = new ArrayList<>();
        list.add(new AbstractMap.SimpleEntry<>("safeScore", stock.getSafeScore() * 4));
        list.add(new AbstractMap.SimpleEntry<>("growthScore", stock.getGrowthScore()));
        list.add(new AbstractMap.SimpleEntry<>("dividendScore", stock.getDividendScore() * 3));
        list.sort(Map.Entry.comparingByValue());

        return new ChangeScoreDto(tradingStockInfoDto.getUserId(), list.get(2).getKey(), list.get(0).getKey());
    }

    Map<String, Object> convertToCombinationMap(List<PurchasedStockDTO> purchasedStockDTOList, Map<String, Stock> stocks) {
        List<List<PurchasedCombinationDTO>> purchasedCombinationDTOList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            purchasedCombinationDTOList.add(new ArrayList<>());
        }

        for (PurchasedStockDTO purchasedStockDTO : purchasedStockDTOList) {
            Stock stock = stocks.get(purchasedStockDTO.getSymbol());

            purchasedCombinationDTOList.get(stock.getDividendMonth() - 1).add(
                    new PurchasedCombinationDTO(stock.getId(), stock.getName(), stock.getSymbol(), purchasedStockDTO.getAmount())
            );
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("date", purchasedStockDTOList.get(0).getDate());
        response.put("combination1", purchasedCombinationDTOList.get(0));
        response.put("combination2", purchasedCombinationDTOList.get(1));
        response.put("combination3", purchasedCombinationDTOList.get(2));

        return response;
    }

    int countStocks(DistributeCombinationForm distributeCombinationForm) {
        int cnt = 0;
        cnt += countStocksOfList(distributeCombinationForm.getCombination1());
        cnt += countStocksOfList(distributeCombinationForm.getCombination2());
        cnt += countStocksOfList(distributeCombinationForm.getCombination3());
        return cnt;
    }

    int countStocksOfList(List<FindStockDetailForm> findStockDetailFormList) {
        int cnt = 0;
        for (FindStockDetailForm findStockDetailForm : findStockDetailFormList) {
            if (findStockDetailForm.getExchange() != null && findStockDetailForm.getStockId() != null) {
                cnt++;
            }
        }
        return cnt;
    }

    List<List<StockDTO>> convertToFixedStockList(DistributeCombinationForm distributeCombinationForm) {
        List<List<StockDTO>> response = new ArrayList<>();
        response.add(createCombination(distributeCombinationForm.getCombination1()));
        response.add(createCombination(distributeCombinationForm.getCombination2()));
        response.add(createCombination(distributeCombinationForm.getCombination3()));
        return response;
    }

    List<StockDTO> createCombination(List<FindStockDetailForm> findStockDetailForm) {
        List<StockDTO> combination = new ArrayList<>();

        for (FindStockDetailForm findStockDetailForm1 : findStockDetailForm) {
            StockDTO stock = findStockAndConvertToStockDTO(findStockDetailForm1.getExchange(), findStockDetailForm1.getStockId());

            if (stock != null) {
                combination.add(stock);
            }
        }
        return combination;
    }

    StockDTO findStockAndConvertToStockDTO(String exchange, Integer stockId) {
        if (exchange == null || stockId == null) {
            return null;
        }

        if (exchange.equals("KSC")) {
            Optional<KrStock> foundKrStock = krStockJpaRepository.findById(stockId);
            if (foundKrStock.isEmpty()) {
                return null;
            }
            Stock krStock = foundKrStock.get();
            return krStock.convertToDTO();
        }

        Optional<UsStock> foundUsStock = usStockJpaRepository.findById(stockId);
        if (foundUsStock.isEmpty()) {
            return null;
        }
        Stock usStock = foundUsStock.get();
        return usStock.convertToDTO();
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
            dividend[combination.getStock().getDividendMonth()] += combination.getDividendPerShareAndQuarter();

            boughtStockPrice += combination.getPrice();
        }

        PriorityQueue<MonthDividend> queueSortedByDividend = new PriorityQueue<>(new Comparator<MonthDividend>() {
            @Override
            public int compare(MonthDividend o1, MonthDividend o2) {
                return Long.compare(o1.getDividend(), o2.getDividend());
            }
        });

        for (int i = 1; i <= 3; i++) {
            if (dividend[i] > 0) {
                queueSortedByDividend.add(new MonthDividend(i, dividend[i]));
            }
        }

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

            lowestDividendMonth.addDividend(combinationToBuy.getDividendPerShareAndQuarter());
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
                combination.add(new Combination(stockDTO, getLatestClosePrice(stockDTO.getExchange(), stockDTO.getId()), 0));
            }
            combinationDTOList.add(combination);
        }
        return combinationDTOList;
    }

    int getLatestClosePrice(String exchange, Integer stockId) {
        if (exchange.equals("KSC")) {
            Optional<KrLatestClose> price = krLatestCloseJpaRepository.findByKrStockId(stockId);
            if (price.isPresent()) {
                double closePrice = price.get().getClose();
                return (int) closePrice;
            }

            throw new NullPointerException();
        }

        Optional<UsLatestClose> price = usLatestCloseJpaRepository.findByUsStockId(stockId);
        if (price.isPresent()) {
            double closePrice = price.get().getClose();
            double krwPrice = ExchangeRate.USD.getBuying() * closePrice;
            return (int) krwPrice;
        }

        throw new NullPointerException();
    }

    Map<String, Object> convertToMap(List<List<Combination>> distirbutedStockList) {
        Map<String, Object> response = new LinkedHashMap<>();
        int group = 1;

        for (List<Combination> combinationList : distirbutedStockList) {
            Map<String, Object> combinationInfo = new LinkedHashMap<>();
            List<CombinationDTO> combinationDTOList = new ArrayList<>();
            long totalDividend = 0;

            for (Combination combination : combinationList) {
                if (combination.getQuantity() > 0) {
                    combinationDTOList.add(combination.convertToDTO());
                    totalDividend += combination.getTotalDividendPerQuarter();
                }
            }

            combinationInfo.put("stocks", combinationDTOList);
            combinationInfo.put("totalDividend", totalDividend);

            response.put("combination" + group++, combinationInfo);
        }

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

        if (searchWord != null) {
            System.out.println("searchWord: " + searchWord);
            combinedStockList.addAll(krStockJpaRepository.findAllByNameContainingOrEnglishNameContaining(searchWord, searchWord));
            combinedStockList.addAll(usStockJpaRepository.findAllByNameContainingOrEnglishNameContaining(searchWord, searchWord));

            if (dividendMonth != null) {
                return combinedStockList.stream().filter(stock -> stock.getDividendMonth().equals(dividendMonth)).collect(Collectors.toList());
            }

            return combinedStockList;
        }

        if (dividendMonth != null) {
            combinedStockList.addAll(krStockJpaRepository.findAllByDividendMonth(dividendMonth));
            combinedStockList.addAll(usStockJpaRepository.findAllByDividendMonth(dividendMonth));
            return combinedStockList;
        }

        combinedStockList.addAll(krStockJpaRepository.findAll());
        combinedStockList.addAll(usStockJpaRepository.findAll());
        return combinedStockList;
    }

    <T> List<T> getPagedList(List<T> stockDTOList, int page, int size) {
        int start = size * page;
        int end = start + size;

        if (start >= stockDTOList.size()) {
            return List.of();
        }

        return stockDTOList.subList(start, Math.min(end, stockDTOList.size()));
    }

    List<StockDTO> getStockDTOList(List<Stock> stockList, FindStocksForm findStocksForm) {
        return stockList.stream()
                .map(stock -> stock.convertToDTO(
                        findStocksForm.getSafeScore(), findStocksForm.getGrowthScore(), findStocksForm.getDividendScore()))
                .collect(Collectors.toList());
    }
}
