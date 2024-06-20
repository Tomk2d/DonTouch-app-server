package donTouch.user_server.like.service;

import donTouch.user_server.like.domain.LikeEnergyFund;
import donTouch.user_server.like.domain.LikeEnergyFundJpaRepository;
import donTouch.user_server.like.domain.LikeEstateFund;
import donTouch.user_server.like.domain.LikeEstateFundJpaRepository;
import donTouch.user_server.like.dto.LikeFundForm;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class LikeFundServiceImpl implements LikeFundService {
    LikeEnergyFundJpaRepository likeEnergyFundJpaRepository;
    LikeEstateFundJpaRepository likeEstateFundJpaRepository;

    @Override
    public Map<String, String> likeEnergyFund(LikeFundForm likeFundForm) {
        LikeEnergyFund likeEnergyFund = likeEnergyFundJpaRepository.save(likeFundForm.convertToLikeEnergyFund());

        Map<String, String> result = new HashMap<>();
        result.put("savedLikeEnergyFundId", likeEnergyFund.getEnergyFundId());
        return result;
    }

    @Override
    public Map<String, Integer> likeEstateFund(LikeFundForm likeFundForm) {
        LikeEstateFund likeEstateFund = likeEstateFundJpaRepository.save(likeFundForm.convertToLikeEstateFund());

        Map<String, Integer> result = new HashMap<>();
        result.put("savedLikeEnergyFundId", likeEstateFund.getEstateFundId());
        return result;
    }

    @Override
    public List<String> findLikeEnergyFunds(Long userId) {
        List<LikeEnergyFund> likeEnergyFunds = likeEnergyFundJpaRepository.findAllByUserId(userId);

        List<String> result = new ArrayList<>();
        for (LikeEnergyFund likeEnergyFund : likeEnergyFunds) {
            result.add(likeEnergyFund.getEnergyFundId());
        }

        return result;
    }

    @Override
    public List<Integer> findLikeEstateFunds(Long userId) {
        List<LikeEstateFund> likeEstateFunds = likeEstateFundJpaRepository.findAllByUserId(userId);

        List<Integer> result = new ArrayList<>();
        for (LikeEstateFund likeEstateFund : likeEstateFunds) {
            result.add(likeEstateFund.getEstateFundId());
        }

        return result;
    }

    @Override
    @Transactional
    public Boolean dislikeEnergyFund(LikeFundForm likeFundForm) {
        likeEnergyFundJpaRepository.deleteByUserIdAndEnergyFundId(likeFundForm.getUserId(), likeFundForm.getEnergyFundId());
        Optional<LikeEnergyFund> deletedFund = likeEnergyFundJpaRepository.findByUserIdAndEnergyFundId(likeFundForm.getUserId(), likeFundForm.getEnergyFundId());

        return deletedFund.isEmpty();
    }

    @Override
    @Transactional
    public Boolean dislikeEstateFund(LikeFundForm likeFundForm) {
        likeEstateFundJpaRepository.deleteByUserIdAndEstateFundId(likeFundForm.getUserId(), likeFundForm.getEstateFundId());
        Optional<LikeEstateFund> deletedFund = likeEstateFundJpaRepository.findByUserIdAndEstateFundId(likeFundForm.getUserId(), likeFundForm.getEstateFundId());

        return deletedFund.isEmpty();
    }
}
