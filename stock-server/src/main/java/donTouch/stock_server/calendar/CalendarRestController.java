package donTouch.stock_server.calendar;

import donTouch.stock_server.calendar.dto.CalendarForm;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
public class CalendarRestController {
    public ApiUtils.ApiResult<String> findCalendar(@RequestBody @Valid CalendarForm calendarForm) {

        return ApiUtils.success(calendarForm.toString());
    }
}
