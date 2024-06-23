package donTouch.order_server.kafka.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonSerialize
public class TradingStockInfoDto {
    private Long userId;
    private Boolean isKr;
    private String symbol;
}
