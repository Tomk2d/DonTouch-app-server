package donTouch.stock_server.dividend;

import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import donTouch.stock_server.dividend.service.DividendService;
import donTouch.stock_server.web.Web;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping("/api/stocks/calendar")
public class DividendRestController {
    private final DividendService dividendService;

    @PostMapping("")
    public ApiUtils.ApiResult<List<DividendDTO>> findCalendar(@RequestBody @Valid DividendForm dividendForm) {
        Map<String, List<String>> holdingStockResponse = Web.getHoldingStockDTOList(dividendForm.getUserId(), false);

        List<DividendDTO> result = dividendService.findCalendar(dividendForm, holdingStockResponse);

        if (result == null) {
            return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ApiUtils.success(result);
    }
}
