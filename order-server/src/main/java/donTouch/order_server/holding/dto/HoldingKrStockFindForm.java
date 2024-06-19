package donTouch.order_server.holding.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
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
public class HoldingKrStockFindForm {
    @NotNull
    private Long userId;
    @NotNull
    private String krStockId;
    @NotNull
    private int krStockAmount;
}
