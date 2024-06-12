package donTouch.stock_server.stock.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindStockForm {
    @NotNull(message = "input userInvestmentType")
    @Min(value = 0, message = "userInvestmentType must be between 0 and 4")
    @Max(value = 4, message = "userInvestmentType must be between 0 and 4")
    private Integer userInvestmentType;

    @NotNull(message = "input safeScore")
    @Min(value = 0, message = "safeScore must be between 0 and 100")
    @Max(value = 100, message = "safeScore must be between 0 and 100")
    private Integer safeScore;

    @NotNull(message = "input dividendScore")
    @Min(value = 0, message = "dividendScore must be between 0 and 100")
    @Max(value = 100, message = "dividendScore must be between 0 and 100")
    private Integer dividendScore;

    @NotNull(message = "input growthScore")
    @Min(value = 0, message = "growthScore must be between 0 and 100")
    @Max(value = 100, message = "growthScore must be between 0 and 100")
    private Integer growthScore;

    @Min(value = 1, message = "dividendMonth must be between 1 and 3")
    @Max(value = 3, message = "dividendMonth must be between 1 and 3")
    private Integer dividendMonth;

    @NotNull(message = "input page")
    @Min(value = 0, message = "page must be 0 or more")
    private Integer page;

    @NotNull(message = "input size")
    @Min(value = 0, message = "size must be 0 or more")
    private Integer size;
}
