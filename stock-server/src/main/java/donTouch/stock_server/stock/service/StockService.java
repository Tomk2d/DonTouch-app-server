package donTouch.stock_server.stock.service;

import donTouch.stock_server.stock.dto.*;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

public interface StockService {
    List<StockDTO> findStocks(FindStocksForm findStockRes);

    Map<String, Object> findStockDetail(FindStockDetailForm findStockDetailForm) throws InstanceNotFoundException;

    Map<String, Object> findStockPrices(FindStockPricesForm findStockPricesForm) throws InstanceNotFoundException;

    Map<String, Object> findCombination(FindCombinationForm findCombinationForm);

    Map<String, Object> distributeCombination(DistributeCombinationForm distributeCombinationForm);

}
