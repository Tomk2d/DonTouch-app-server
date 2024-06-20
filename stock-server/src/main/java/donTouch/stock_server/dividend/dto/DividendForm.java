package donTouch.stock_server.dividend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DividendForm {
    @NotNull(message = "input userId")
    private Long userId;

    @NotNull(message = "input startDate")
    private LocalDate startDate;

    @NotNull(message = "input endDate")
    private LocalDate endDate;
}
