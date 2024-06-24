package donTouch.stock_server.stock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindCombinationForm {
    @NotNull(message = "input userId")
    private Long userId;

    @NotNull(message = "input investmentAmount")
    @Min(value = 0, message = "investmentAmount must be 0 or more")
    private Long investmentAmount;
}
