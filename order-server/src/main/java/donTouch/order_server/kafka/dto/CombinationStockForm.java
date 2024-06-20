package donTouch.order_server.kafka.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonDeserialize
@JsonSerialize
@AllArgsConstructor
public class CombinationStockForm {
    private Long combinationId;
    private String stockName;
    private Long userId;
    private String krHoldingStockId;
    private int krStockAmount;
    private int krStockPrice;
    private Long krTotalPrice;
    private Long inOutCash;
    private int inOutType;
}
