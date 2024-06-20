package donTouch.order_server.holding;
import donTouch.order_server.holding.domain.HoldingKrStock;
import donTouch.order_server.holding.domain.HoldingUsStock;
import donTouch.order_server.holding.dto.*;
import donTouch.order_server.bankAccount.dto.UserBankAccountLogDto;
import donTouch.order_server.bankAccount.service.BankAccountService;
import donTouch.order_server.holding.service.HoldingEnergyFundService;
import donTouch.order_server.holding.service.HoldingEstateFundService;
import donTouch.order_server.holding.service.HoldingKrStockService;
import donTouch.order_server.holding.service.HoldingUsStockService;
import donTouch.utils.utils.ApiUtils;
import donTouch.utils.utils.ApiUtils.ApiResult;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
public class HoldingRestController {
    private final HoldingEstateFundService holdingEstateFundService;
    private final HoldingKrStockService holdingKrStockService;
    private final HoldingUsStockService holdingUsStockService;
    private final HoldingEnergyFundService holdingEnergyFundService;
    private final BankAccountService bankAccountService;


    @GetMapping("/api/holding/account/{userId}")
    public ApiResult<List<UserBankAccountLogDto>> allBankLog(@PathVariable Long userId){
        try{
            List<UserBankAccountLogDto> result = bankAccountService.getUserBankAccountLog(userId);
            if(result == null){
                return ApiUtils.error("입출금 내역이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/holding/allEstate/{userId}")
    public ApiResult<List<HoldingEstateFundDto>> allEstate(@PathVariable Long userId) {
        try {
            List<HoldingEstateFundDto> result = holdingEstateFundService.getAllEstate(userId);
            if (result == null) {
                return ApiUtils.error("보유 부동산 상품이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        } catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/holding/allEnergy/{userId}")
    public ApiResult<List<HoldingEnergyFundDto>> allEnergy(@PathVariable Long userId) {
        try {
            List<HoldingEnergyFundDto> result = holdingEnergyFundService.getAllEnergy(userId);
            if (result == null) {
                return ApiUtils.error("보유 에너지 상품이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        } catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/holding/estate/sell")
    public ResponseEntity<Object> findEstateAndDelete(@RequestBody HoldingEstateFundForm holdingEstateFundForm) {
        try {
            HoldingEstateFundDto result = holdingEstateFundService.findByUserIdAndEstateFundId(holdingEstateFundForm);
            return ResponseEntity.ok(result);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/api/holding/sell/krStock")
    public ApiResult<Object> findByUserIdAndStockId(@RequestBody @Valid HoldingKrStockFindForm holdingKrStockFindForm) {
        try {
            HoldingKrStock result = holdingKrStockService.sellStockUpdate(holdingKrStockFindForm);
            return ApiUtils.success(result);
        } catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/holding/energy/sell")
    public ResponseEntity<Object> findEnergyAndDelete(@RequestBody HoldingEnergyFundForm holdingEnergyFundForm) {
        try {
            HoldingEnergyFundDto result = holdingEnergyFundService.findByUserIdAndEnergyFundId(holdingEnergyFundForm);
            return ResponseEntity.ok(result);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/api/holding/energy/totalCash/{userId}")
    public ApiResult<Integer> totalEnergyCash(@PathVariable Long userId) {
        try {
            Integer result = holdingEnergyFundService.getEnergyTotalCash(userId);
            if (result == null) {
                return ApiUtils.error("보유 주식이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        } catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/holding/estate/totalCash/{userId}")
    public ApiResult<Integer> totalEstateCash(@PathVariable Long userId) {
        try {
            Integer result = holdingEstateFundService.getEstateTotalCash(userId);
            if (result == null) {
                return ApiUtils.error("보유 주식이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        } catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/holding/energy/calendar")
    public ApiUtils.ApiResult<List<DividendP2PDto>> getEnergyCanlendar(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CalendarReqForm calendarReqForm){
        try{
            List<DividendP2PDto> result = holdingEnergyFundService.getEnergyDividend(calendarReqForm, token);
            return ApiUtils.success(result);
        }catch(NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/holding/estate/calendar")
    public ApiUtils.ApiResult<List<DividendP2PDto>> getEstateCanlendar(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CalendarReqForm calendarReqForm) {
        try {
            List<DividendP2PDto> result = holdingEstateFundService.getEstateDividend(calendarReqForm, token);
            return ApiUtils.success(result);
        } catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    };

    @GetMapping("/api/holding/stocks")
    public ApiResult<Map<String, Object>> getHoldingStockIds(@RequestParam("userId") Long userId, @RequestParam("getPrice") Boolean getPrice) {
        Map<String, Object> result = new LinkedHashMap<>();

        List<String> holdingKrStockSymbols = holdingKrStockService.findHoldingStockIds(userId);
        List<String> holdingUsStockSymbols = holdingUsStockService.findHoldingStockIds(userId);

        if (holdingKrStockSymbols == null || holdingUsStockSymbols == null) {
            return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!getPrice) {
            result.put("krSymbols", holdingKrStockSymbols);
            result.put("usSymbols", holdingUsStockSymbols);
            return ApiUtils.success(result);
        }

        // getPrice == true 면 매수단가, 수량 같이 받아서 전달하기
        return ApiUtils.error("can't get price now", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/api/holding/sell/usStock")
    public ApiResult<Object> findByUserIdAndStockIdUs(@RequestBody @Valid HoldingUsStockFindForm holdingUsStockFindForm) {
        try {
            HoldingUsStock result = holdingUsStockService.sellStockUpdate(holdingUsStockFindForm);
            return ApiUtils.success(result);
        } catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
