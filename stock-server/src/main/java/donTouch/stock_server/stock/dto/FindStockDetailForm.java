package donTouch.stock_server.stock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FindStockDetailForm {
    @NotBlank(message = "input exchange")
    private String exchange;

    @NotNull(message = "input stockId")
    private Integer stockId;
}
