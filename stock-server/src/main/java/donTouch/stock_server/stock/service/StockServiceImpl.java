package donTouch.stock_server.stock.service;

import donTouch.stock_server.krStock.domain.KrStock;
import donTouch.stock_server.krStock.domain.KrStockJpaRepository;
import donTouch.stock_server.stock.domain.Stock;
import donTouch.stock_server.stock.domain.StockDTO;
import donTouch.stock_server.stock.dto.FindStockForm;
import donTouch.stock_server.usStock.domain.UsStock;
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
        List<UsStock> usStockList;
        List<KrStock> krStockList;

        if (findStockForm.getDividendMonth() == null) {
            usStockList = usStockJpaRepository.findAll();
            krStockList = krStockJpaRepository.findAll();
        } else {
            usStockList = usStockJpaRepository.findByDividendMonth(findStockForm.getDividendMonth());
            krStockList = krStockJpaRepository.findByDividendMonth(findStockForm.getDividendMonth());
        }
        
        List<Stock> combinedStockList = new ArrayList<>();
        combinedStockList.addAll(usStockList);
        combinedStockList.addAll(krStockList);

        combinedStockList.sort(Comparator.comparing(Stock::getDividendYieldTtm).reversed());

        int start = findStockForm.getSize() * findStockForm.getPage();
        int end = start + findStockForm.getSize();

        List<Stock> pagedStockList = combinedStockList.subList(start, end);

        return pagedStockList.stream()
                .map(Stock::convertToStockDTO)
                .toList();
    }

}
