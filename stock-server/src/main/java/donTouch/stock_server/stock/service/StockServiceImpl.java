package donTouch.stock_server.stock.service;

import donTouch.stock_server.krStock.domain.KrStockJpaRepository;
import donTouch.stock_server.stock.domain.Stock;
import donTouch.stock_server.stock.dto.FindStockForm;
import donTouch.stock_server.stock.dto.StockDTO;
import donTouch.stock_server.usStock.domain.UsStockJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {
    private final UsStockJpaRepository usStockJpaRepository;
    private final KrStockJpaRepository krStockJpaRepository;

    @Override
    public List<StockDTO> findStock(FindStockForm findStockForm) {
        List<Stock> combinedStockList;
        if (findStockForm.getDividendMonth() == null) {
            combinedStockList = getCombinedStockList();
        } else {
            combinedStockList = getCombinedStockListFilteredByMonth(findStockForm.getDividendMonth());
        }

        combinedStockList.sort(Comparator.comparing(Stock::getDividendYieldTtm).reversed());

        List<Stock> pagedStockList = getPagedStockList(combinedStockList, findStockForm.getPage(), findStockForm.getSize());

        return getStockDTOList(pagedStockList);
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
                .map(Stock::convertToStockDTO)
                .toList();
    }
}
