package donTouch.energy_server.energy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BuyEnergyFundForm {
    @NotNull(message = "유저아이디가 없습니다.")
    private Long userId;
    @NotNull(message = "에너지 id 가 없습니다.")
    private String energyFundId;
    @NotNull(message = "투자금액이 없습니다.")
    private int inputCash;
    @NotNull(message = "에너지 이름이 없습니다.")
    private String energyName;
    @NotNull(message = "에너지 이익률이 없습니다.")
    private double energyEarningRate;
}
