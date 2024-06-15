package donTouch.estate_server.estate.dto;

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
public class BuyEstateFundForm {
    @NotNull(message = "유저아이디가 없습니다.")
    private Long userId;
    @NotNull(message = "부동산 id 가 없습니다.")
    private int estateFundId;
    @NotNull(message = "투자금액이 없습니다.")
    private int inputCash;
    @NotNull(message = "부동산 이름이 없습니다.")
    private String estateName;
    @NotNull(message = "부동산 이익률이 없습니다.")
    private double estateEarningRate;
}
