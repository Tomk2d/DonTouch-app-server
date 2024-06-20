package donTouch.user_server.like.service;

import donTouch.user_server.like.domain.LikeKrStock;
import donTouch.user_server.like.domain.LikeKrStockJpaRepository;
import donTouch.user_server.like.domain.LikeUsStock;
import donTouch.user_server.like.domain.LikeUsStockJpaRepository;
import donTouch.user_server.like.dto.LikeStockDTO;
import donTouch.user_server.like.dto.LikeStockForm;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeStockServiceImpl implements LikeStockService {
    LikeKrStockJpaRepository likeKrStockJpaRepository;
    LikeUsStockJpaRepository likeUsStockJpaRepository;

    @Override
    public LikeStockDTO likeStock(LikeStockForm likeStockForm) {
        if (likeStockForm.getExchange().equals("KSC")) {
            LikeKrStock likeKrStock = likeKrStockJpaRepository.save(likeStockForm.convertToLikeKrStock());
            return likeKrStock.convertToDTO();
        }

        LikeUsStock likeUsStock = likeUsStockJpaRepository.save(likeStockForm.convertToLikeUsStock());
        return likeUsStock.convertToDTO();
    }

    @Override
    public List<LikeStockDTO> findLikeStocks(Long userId) {
        List<LikeKrStock> likeKrStockList = likeKrStockJpaRepository.findAllByUserId(userId);
        List<LikeUsStock> likeUsStockList = likeUsStockJpaRepository.findAllByUserId(userId);

        List<LikeStockDTO> likeStockDTOList = new ArrayList<>();
        for (LikeKrStock likeKrStock : likeKrStockList) {
            likeStockDTOList.add(likeKrStock.convertToDTO());
        }

        for (LikeUsStock likeUsStock : likeUsStockList) {
            likeStockDTOList.add(likeUsStock.convertToDTO());
        }

        return likeStockDTOList;
    }

    @Override
    @Transactional
    public Boolean dislikeStock(LikeStockForm likeStockForm) {
        if (likeStockForm.getExchange().equals("KSC")) {
            likeKrStockJpaRepository.deleteByUserIdAndKrStockId(likeStockForm.getUserId(), likeStockForm.getStockId());
            Optional<LikeKrStock> deletedLikeStock = likeKrStockJpaRepository.findByUserIdAndKrStockId(likeStockForm.getUserId(), likeStockForm.getStockId());
            return deletedLikeStock.isEmpty();
        }

        likeUsStockJpaRepository.deleteByUserIdAndUsStockId(likeStockForm.getUserId(), likeStockForm.getStockId());
        Optional<LikeUsStock> deletedLikeStock = likeUsStockJpaRepository.findByUserIdAndUsStockId(likeStockForm.getUserId(), likeStockForm.getStockId());
        return deletedLikeStock.isEmpty();
    }
}
