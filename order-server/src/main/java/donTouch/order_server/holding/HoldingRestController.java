package donTouch.order_server.holding;

import donTouch.order_server.holding.dto.BankCalculateForm;
import donTouch.order_server.holding.dto.HoldingEstateFundDto;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;
import donTouch.order_server.holding.service.HoldingEstateFundService;
import donTouch.utils.utils.ApiUtils;
import donTouch.utils.utils.ApiUtils.ApiResult;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class HoldingRestController {
    private final HoldingEstateFundService holdingEstateFundService;

    @GetMapping("/api/holding/allEstate/{userId}")
    public ApiResult<List<HoldingEstateFundDto>> allEstate(@PathVariable Long userId) {
        try{
            List<HoldingEstateFundDto> result = holdingEstateFundService.getAllEstate(userId);
            if (result == null) {
                return ApiUtils.error("보유 주식이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/holding/estate/sell")
    public ResponseEntity<Object> findEstateAndDelete(@RequestBody HoldingEstateFundForm holdingEstateFundForm) {
        try{
            HoldingEstateFundDto result = holdingEstateFundService.findByUserIdAndEstateFundId(holdingEstateFundForm);
            return ResponseEntity.ok(result);
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }
}
