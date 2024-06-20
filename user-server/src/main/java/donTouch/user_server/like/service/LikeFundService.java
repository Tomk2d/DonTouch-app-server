package donTouch.user_server.like.service;

import donTouch.user_server.like.dto.LikeFundForm;

import java.util.List;
import java.util.Map;

public interface LikeFundService {
    Map<String, String> likeEnergyFund(LikeFundForm likeFundForm);

    Map<String, Integer> likeEstateFund(LikeFundForm likeFundForm);

    List<String> findLikeEnergyFunds(Long userId);

    List<Integer> findLikeEstateFunds(Long userId);

    Boolean dislikeEnergyFund(LikeFundForm likeFundForm);

    Boolean dislikeEstateFund(LikeFundForm likeFundForm);
}
