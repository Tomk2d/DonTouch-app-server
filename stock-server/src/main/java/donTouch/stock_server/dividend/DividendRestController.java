package donTouch.stock_server.dividend;

import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import donTouch.stock_server.dividend.service.DividendService;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/calendar")
public class DividendRestController {
    private final DividendService dividendService;

    @PostMapping("")
    public ApiUtils.ApiResult<List<DividendDTO>> findCalendar(@RequestBody @Valid DividendForm dividendForm) {
        List<DividendDTO> result = dividendService.findCalendar(dividendForm);

        if (result == null) {
            return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ApiUtils.success(result);
    }
}
