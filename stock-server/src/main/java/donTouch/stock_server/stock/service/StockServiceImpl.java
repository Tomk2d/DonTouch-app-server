package donTouch.stock_server.stock.service;

import donTouch.stock_server.krStock.domain.KrStock;
import donTouch.stock_server.krStock.domain.KrStockDetail;
import donTouch.stock_server.krStock.domain.KrStockDetailJpaRepository;
import donTouch.stock_server.krStock.domain.KrStockJpaRepository;
import donTouch.stock_server.stock.domain.Stock;
import donTouch.stock_server.stock.dto.FindStockDetailForm;
import donTouch.stock_server.stock.dto.FindStocksForm;
import donTouch.stock_server.stock.dto.StockDTO;
import donTouch.stock_server.usStock.domain.UsStock;
import donTouch.stock_server.usStock.domain.UsStockDetail;
import donTouch.stock_server.usStock.domain.UsStockDetailJpaRepository;
import donTouch.stock_server.usStock.domain.UsStockJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.*;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {
    private final KrStockJpaRepository krStockJpaRepository;
    private final UsStockJpaRepository usStockJpaRepository;

    private final KrStockDetailJpaRepository krStockDetailJpaRepository;
    private final UsStockDetailJpaRepository usStockDetailJpaRepository;

    @Override
    public List<StockDTO> findStocks(FindStocksForm findStocksForm) {
        List<Stock> combinedStockList;
        if (findStocksForm.getDividendMonth() == null) {
            combinedStockList = getCombinedStockList();
        } else {
            combinedStockList = getCombinedStockListFilteredByMonth(findStocksForm.getDividendMonth());
        }

        combinedStockList.sort(Comparator.comparing(Stock::getDividendYieldTtm).reversed());

        List<Stock> pagedStockList = getPagedStockList(combinedStockList, findStocksForm.getPage(), findStocksForm.getSize());

        return getStockDTOList(pagedStockList);
    }

    @Override
    public Map<String, Object> findStockDetail(FindStockDetailForm findStockDetailForm) throws InstanceNotFoundException {
        Map<String, Object> stockDetail = new HashMap<>();

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


    List<Stock> getCombinedStockList() {
        List<Stock> combinedStockList = new ArrayList<>();

        combinedStockList.addAll(usStockJpaRepository.findAll());
        combinedStockList.addAll(krStockJpaRepository.findAll());

        return combinedStockList;
    }

    List<Stock> getCombinedStockListFilteredByMonth(int month) {
        List<Stock> combinedStockList = new ArrayList<>();

        combinedStockList.addAll(usStockJpaRepository.findByDividendMonth(month));
        combinedStockList.addAll(krStockJpaRepository.findByDividendMonth(month));

        return combinedStockList;
    }

    List<Stock> getPagedStockList(List<Stock> stockList, int page, int size) {
        int start = size * page;
        int end = start + size;

        return stockList.subList(start, end);
    }

    List<StockDTO> getStockDTOList(List<Stock> stockList) {
        return stockList.stream()
                .map(Stock::convertToDTO)
                .toList();
    }
}
