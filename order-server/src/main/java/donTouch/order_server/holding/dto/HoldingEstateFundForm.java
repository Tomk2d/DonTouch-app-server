package donTouch.order_server.holding.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
@AllArgsConstructor
public class HoldingEstateFundForm {
    @NotNull(message = "유저아이디가 없습니다.")
    private Long userId;
    @NotNull(message = "에너지 id가 없습니다.")
    private int estateId;
    @NotNull(message = "이미지가 없습니다.")
    private String titleImageUrl;
    @NotNull(message = "에너지 이름이 없습니다.")
    private String title;
    @NotNull(message = "에너지 이익률이 없습니다.")
    private double earningRate;
    @NotNull(message = "투자 기간이 없습니다.")
    private int investmentPeriod;
    @NotNull(message = "투자 금액이 없습니다.")
    private int inputCash;
    @NotNull(message = "시작 날짜가 없습니다.")
    private LocalDateTime startPeriod;

}
