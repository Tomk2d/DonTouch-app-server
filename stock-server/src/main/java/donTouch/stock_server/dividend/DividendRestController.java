package donTouch.stock_server.dividend;

import donTouch.stock_server.dividend.dto.DividendForm;
import donTouch.stock_server.dividend.service.DividendService;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/calendar")
public class DividendRestController {
    private final DividendService dividendService;

    @PostMapping("")
    public ApiUtils.ApiResult<String> findCalendar(@RequestBody @Valid DividendForm dividendForm) {
        dividendService.findCalendar(dividendForm);
        return ApiUtils.success(dividendForm.getStartDate().toString());
    }
}
