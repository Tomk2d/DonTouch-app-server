package donTouch.stock_server.calendar;

import donTouch.utils.utils.ApiUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
public class CalendarRestController {
    public ApiUtils.ApiResult<String> findCalendar() {

        return ApiUtils.success("success");
    }
}
