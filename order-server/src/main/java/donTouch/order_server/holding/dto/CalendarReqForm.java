package donTouch.order_server.holding.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.LocalDate;
@Getter
public class CalendarReqForm {
    @NotNull(message = "input startDate")
    private LocalDate startDate;
    @NotNull(message = "input endDate")
    private LocalDate endDate;
}
