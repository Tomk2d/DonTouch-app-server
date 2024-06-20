package donTouch.estate_server.kafka.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@JsonDeserialize
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountLogDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long inOutCash;
    @NotNull
    private int inOutType; // 0 : out, 1 : in
    @NotNull
    @Size(min = 1, max = 30)
    private String inOutTitle;

    private LocalDateTime inOutTime;
}

