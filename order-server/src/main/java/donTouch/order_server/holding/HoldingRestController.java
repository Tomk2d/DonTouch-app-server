package donTouch.order_server.holding;

import donTouch.order_server.holding.dto.*;
import donTouch.order_server.holding.service.HoldingEnergyFundService;
import donTouch.order_server.holding.service.HoldingEstateFundService;
import donTouch.utils.utils.ApiUtils;
import donTouch.utils.utils.ApiUtils.ApiResult;
import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
public class HoldingRestController {
    private final HoldingEstateFundService holdingEstateFundService;
    private final HoldingEnergyFundService holdingEnergyFundService;

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

    @GetMapping("/api/holding/allEnergy/{userId}")
    public ApiResult<List<HoldingEnergyFundDto>> allEnergy(@PathVariable Long userId) {
        try{
            List<HoldingEnergyFundDto> result = holdingEnergyFundService.getAllEnergy(userId);
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

    @PostMapping("/api/holding/energy/sell")
    public ResponseEntity<Object> findEnergyAndDelete(@RequestBody HoldingEnergyFundForm holdingEnergyFundForm) {
        try{
            HoldingEnergyFundDto result = holdingEnergyFundService.findByUserIdAndEnergyFundId(holdingEnergyFundForm);
            return ResponseEntity.ok(result);
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/api/holding/energy/totalCash/{userId}")
    public ApiResult<Integer> totalEnergyCash(@PathVariable Long userId) {
        try{
            Integer result = holdingEnergyFundService.getEnergyTotalCash(userId);
            if (result == null) {
                return ApiUtils.error("보유 주식이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/holding/estate/totalCash/{userId}")
    public ApiResult<Integer> totalEstateCash(@PathVariable Long userId) {
        try{
            Integer result = holdingEstateFundService.getEstateTotalCash(userId);
            if (result == null) {
                return ApiUtils.error("보유 주식이 없습니다.", HttpStatus.NOT_FOUND);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/holding/energy/calendar")
    public ApiUtils.ApiResult<List<DividendEnergyDto>> getEnergyCanlendar(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid CalendarReqForm calendarReqForm){
        try{
            List<DividendEnergyDto> result = holdingEnergyFundService.getEnergyDividend(calendarReqForm, token);
            return ApiUtils.success(result);
        }catch(NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
