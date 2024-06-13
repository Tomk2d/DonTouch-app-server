package donTouch.stock_server.stock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindStockPricesForm {
    @NotBlank(message = "input exchange")
    private String exchange;

    @NotNull(message = "input stockId")
    @Min(value = 0, message = "stockId should be 1 or more")
    private Integer stockId;

    @NotNull(message = "input month")
    @Min(value = 1, message = "month should be 1 or more")
    private Integer month;

    @Min(value = 1, message = "interval should be 1 or more")
    @NotNull(message = "input interval")
    private Integer interval;
}
