package donTouch.stock_server.stock.service;

import donTouch.stock_server.stock.domain.StockDTO;
import donTouch.stock_server.stock.dto.FindStockForm;

import java.util.List;

public interface StockService {
    List<StockDTO> findStock(FindStockForm findStockRes);
}
