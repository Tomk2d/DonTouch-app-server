package donTouch.stock_server.krStock.service;

import donTouch.stock_server.krStock.domain.KrStock;
import org.springframework.data.domain.Page;

public interface KrStockService {
  public Page<KrStock> findProducts(int page, int size);
}
