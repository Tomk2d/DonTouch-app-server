package donTouch.user_server.like.service;

import donTouch.user_server.like.dto.LikeStockDTO;
import donTouch.user_server.like.dto.LikeStockForm;

import java.util.Map;

public interface LikeStockService {
    LikeStockDTO likeKrStock(LikeStockForm likeStockForm);

    LikeStockDTO likeUsStock(LikeStockForm likeStockForm);

    Map<String, Object> findLikeStocks(Long userId);
}
