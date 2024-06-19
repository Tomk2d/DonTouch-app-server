package donTouch.user_server.like.service;

import donTouch.user_server.like.dto.LikeStockDTO;
import donTouch.user_server.like.dto.LikeStockForm;

import java.util.List;

public interface LikeStockService {
    LikeStockDTO likeKrStock(LikeStockForm likeStockForm);

    LikeStockDTO likeUsStock(LikeStockForm likeStockForm);

    LikeStockDTO likeStock(LikeStockForm likeStockForm);

    List<LikeStockDTO> findLikeStocks(Long userId);

    Boolean dislikeStock(LikeStockForm likeStockForm);
}
