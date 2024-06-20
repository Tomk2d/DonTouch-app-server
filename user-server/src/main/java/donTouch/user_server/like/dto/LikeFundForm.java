package donTouch.user_server.like.dto;

import donTouch.user_server.like.domain.LikeEnergyFund;
import donTouch.user_server.like.domain.LikeEstateFund;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LikeFundForm {
    @NotNull(message = "input userId")
    private Long userId;

    private Integer estateFundId;

    private String energyFundId;

    public LikeEnergyFund convertToLikeEnergyFund() {
        return new LikeEnergyFund((long) 0, userId, energyFundId);
    }

    public LikeEstateFund convertToLikeEstateFund() {
        return new LikeEstateFund((long) 0, userId, estateFundId);
    }
}
