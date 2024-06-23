package donTouch.user_server.kafka.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonDeserialize
@Getter
public class ChangeScoreDto {
    private Long userId;
    private String highestScoreOfPurchasedStock;
    private String lowestScoreOfPurchasedStock;
}
