package donTouch.stock_server.exchange;

import donTouch.utils.exchangeRate.ExchangeRate;
import donTouch.utils.utils.ApiUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping("/api/exchange")
public class ExchangeRestController {
    @GetMapping("/usd")
    public ApiUtils.ApiResult<ExchangeRate> getUsdExchangeRate() {
        // test commit
        return ApiUtils.success(ExchangeRate.USD);
    }
}
