package donTouch.stock_server.kafka.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonSerialize
@Getter
public class ChangeScoreDto {
    private Long userId;
    private String highestScoreOfPurchasedStock;
    private String lowestScoreOfPurchasedStock;
}
