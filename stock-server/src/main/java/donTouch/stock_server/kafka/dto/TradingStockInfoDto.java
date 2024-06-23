package donTouch.stock_server.kafka.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonDeserialize
@NoArgsConstructor
public class TradingStockInfoDto {
    private Long userId;
    private Boolean isKr;
    private String symbol;
}
