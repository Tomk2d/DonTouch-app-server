package donTouch.stock_server.stock.service;

import donTouch.stock_server.kafka.dto.ChangeScoreDto;
import donTouch.stock_server.kafka.dto.TradingStockInfoDto;
import donTouch.stock_server.stock.dto.*;
import donTouch.stock_server.web.dto.LikeStockDTO;
import donTouch.stock_server.web.dto.PurchaseInfoDTO;
import donTouch.stock_server.web.dto.PurchasedStockDTO;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

public interface StockService {
    List<StockDTO> findStocks(FindStocksForm findStockRes);

    Map<String, Object> findStockDetail(FindStockDetailForm findStockDetailForm) throws InstanceNotFoundException;

    Map<String, Object> findStockPrices(FindStockPricesForm findStockPricesForm) throws InstanceNotFoundException;

    Map<String, Object> findCombination(FindCombinationForm findCombinationForm);

    Map<String, Object> distributeCombination(DistributeCombinationForm distributeCombinationForm);

    Map<String, Object> findLikeStocks(List<LikeStockDTO> likeStockDTOList);

    Map<String, Object> findHoldingStocks(Map<String, List<PurchaseInfoDTO>> likeStockDTOList);

    List<Map<String, Object>> findCombinationInfos(List<PurchasedStockDTO> purchasedStockDTOList, Integer page, Integer size);

    ChangeScoreDto requestToChangeUserScore(TradingStockInfoDto tradingStockInfoDto);
}
