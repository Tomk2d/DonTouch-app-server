package donTouch.user_server.like.service;

import donTouch.user_server.like.domain.LikeKrStock;
import donTouch.user_server.like.domain.LikeKrStockJpaRepository;
import donTouch.user_server.like.domain.LikeUsStock;
import donTouch.user_server.like.domain.LikeUsStockJpaRepository;
import donTouch.user_server.like.dto.LikeStockDTO;
import donTouch.user_server.like.dto.LikeStockForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LikeStockServiceImpl implements LikeStockService {
    LikeKrStockJpaRepository likeKrStockJpaRepository;
    LikeUsStockJpaRepository likeUsStockJpaRepository;

    @Override
    public LikeStockDTO likeKrStock(LikeStockForm likeStockForm) {
        LikeKrStock likeKrStock = likeKrStockJpaRepository.save(likeStockForm.convertToLikeKrStock());
        return likeKrStock.convertToDTO();
    }

    @Override
    public LikeStockDTO likeUsStock(LikeStockForm likeStockForm) {
        LikeUsStock likeUsStock = likeUsStockJpaRepository.save(likeStockForm.convertToLikeUsStock());
        return likeUsStock.convertToDTO();
    }

    @Override
    public Map<String, Object> findLikeStocks(Long userId) {
        Map<String, Object> response = new HashMap<>();

        List<LikeKrStock> likeKrStockList = likeKrStockJpaRepository.findAllByUserId(userId);
        List<LikeUsStock> likeUsStockList = likeUsStockJpaRepository.findAllByUserId(userId);

        List<LikeStockDTO> likeKrStockDTOList = new ArrayList<>();
        for (LikeKrStock likeKrStock : likeKrStockList) {
            likeKrStockDTOList.add(likeKrStock.convertToDTO());
        }

        List<LikeStockDTO> likeUsStockDTOList = new ArrayList<>();
        for (LikeUsStock likeUsStock : likeUsStockList) {
            likeUsStockDTOList.add(likeUsStock.convertToDTO());
        }

        response.put("krStocks", likeKrStockDTOList);
        response.put("usStocks", likeUsStockDTOList);

        return response;
    }
}
