package donTouch.stock_server.dividend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DividendForm {
    @NotNull(message = "input token")
    private String token;

    @NotNull(message = "input startDate")
    private LocalDate startDate;

    @NotNull(message = "input endDate")
    private LocalDate endDate;
}
