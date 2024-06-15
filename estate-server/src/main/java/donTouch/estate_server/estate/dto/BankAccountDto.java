package donTouch.estate_server.estate.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
@AllArgsConstructor
public class BankAccountDto {
    @NotNull(message = "id가 없습니다.")
    private Long id;
    @NotNull(message = "userId 가 없습니다.")
    private Long userId;
    @NotNull(message = "잔고가 null 입니다.")
    private Long cash;
}
