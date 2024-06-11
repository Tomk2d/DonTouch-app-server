package donTouch.stock_server.krStock.service;

import donTouch.stock_server.krStock.domain.KrStock;
import donTouch.stock_server.krStock.domain.KrStockJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KrStockServiceImpl implements KrStockService {
  private KrStockJpaRepository krStockJpaRepository;

  @Override
  public Page<KrStock> findProducts(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<KrStock> pageResult = krStockJpaRepository.findAll(pageRequest);

    if (pageResult.getTotalElements() > 0) {
      throw new IllegalStateException();
    }

    return pageResult;
  }
}
