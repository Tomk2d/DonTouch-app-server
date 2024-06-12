package donTouch.stock_server.stock.service;

import donTouch.stock_server.stock.dto.FindStocksForm;
import donTouch.stock_server.stock.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> findStocks(FindStocksForm findStockRes);

}
