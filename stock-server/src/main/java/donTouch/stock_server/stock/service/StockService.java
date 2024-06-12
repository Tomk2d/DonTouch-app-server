package donTouch.stock_server.stock.service;

import donTouch.stock_server.stock.dto.FindStockForm;
import donTouch.stock_server.stock.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> findStock(FindStockForm findStockRes);
}
