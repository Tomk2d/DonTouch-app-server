package donTouch.user_server.like.service;

import donTouch.user_server.like.domain.LikeKrStock;
import donTouch.user_server.like.domain.LikeKrStockJpaRepository;
import donTouch.user_server.like.domain.LikeUsStock;
import donTouch.user_server.like.domain.LikeUsStockJpaRepository;
import donTouch.user_server.like.dto.FindLikeStockForm;
import donTouch.user_server.like.dto.LikeStockDTO;
import donTouch.user_server.like.dto.LikeStockForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<LikeStockDTO> findLikeStocks(FindLikeStockForm findLikeStockForm) {
        System.out.println("userID: " + findLikeStockForm.getUserId());
        List<LikeKrStock> likeKrStockList = likeKrStockJpaRepository.findAllByUserId(findLikeStockForm.getUserId());
        List<LikeUsStock> likeUsStockList = likeUsStockJpaRepository.findAllByUserId(findLikeStockForm.getUserId());

        List<LikeStockDTO> findLikeStockDTOList = new ArrayList<>();
        for (LikeKrStock likeKrStock : likeKrStockList) {
            findLikeStockDTOList.add(likeKrStock.convertToDTO());
        }
        for (LikeUsStock likeUsStock : likeUsStockList) {
            findLikeStockDTOList.add(likeUsStock.convertToDTO());
        }

        return findLikeStockDTOList;
    }
}
