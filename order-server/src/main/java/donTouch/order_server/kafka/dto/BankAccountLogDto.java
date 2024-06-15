package donTouch.order_server.kafka.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@JsonDeserialize
@JsonSerialize
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

    public BankAccountLogDto() {
    }

    public BankAccountLogDto(Long userId, Long inOutCash, int inOutType,
        String inOutTitle) {
        this.userId = userId;
        this.inOutCash = inOutCash;
        this.inOutType = inOutType;
        this.inOutTitle = inOutTitle;
    }
}

