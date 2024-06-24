package donTouch.stock_server.stock.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindStocksForm {
    @NotNull(message = "input userId")
    private Long userId;

    private String searchWord;

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
