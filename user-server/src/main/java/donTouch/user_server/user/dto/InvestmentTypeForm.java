package donTouch.user_server.user.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize
@JsonDeserialize
public class InvestmentTypeForm {
    @NotNull(message = "아이디가 없습니다.")
    private Long userId;
    @NotNull(message = "점수가 없습니다.")
    private Integer totalScore;
}
