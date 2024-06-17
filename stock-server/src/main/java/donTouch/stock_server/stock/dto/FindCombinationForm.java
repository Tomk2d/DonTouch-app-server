package donTouch.stock_server.stock.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindCombinationForm {
    @NotNull(message = "input safeScore")
    @Min(value = 0, message = "safeScore must be between 0 and 100")
    @Max(value = 100, message = "safeScore must be between 0 and 100")
    private Integer safeScore;

    @NotNull(message = "input growthScore")
    @Min(value = 0, message = "growthScore must be between 0 and 100")
    @Max(value = 100, message = "growthScore must be between 0 and 100")
    private Integer growthScore;

    @NotNull(message = "input dividendScore")
    @Min(value = 0, message = "dividendScore must be between 0 and 100")
    @Max(value = 100, message = "dividendScore must be between 0 and 100")
    private Integer dividendScore;

    @NotNull(message = "input investmentAmount")
    @Min(value = 0, message = "investmentAmount must be 0 or more")
    private Integer investmentAmount;
}
