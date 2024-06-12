package donTouch.stock_server.calendar.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CalendarForm {
    @NotBlank(message = "input token")
    String token;

    @NotBlank(message = "input startDate")
    Date startDate;

    @NotBlank(message = "input endDate")
    Date endDate;
}
