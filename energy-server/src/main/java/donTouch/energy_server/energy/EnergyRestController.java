package donTouch.energy_server.energy;

import donTouch.energy_server.energy.dto.*;
import donTouch.energy_server.energy.service.EnergyFundDetailService;
import donTouch.energy_server.energy.service.EnergyFundService;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping("/api/energy")
public class EnergyRestController {
    private EnergyFundService energyFundService;
    private EnergyFundDetailService energyFundDetailService;

    @GetMapping("")
    public ApiUtils.ApiResult<List<EnergyFundDto>> getAllEnergy(){
        try{
            List<EnergyFundDto> resultList = energyFundService.getAllEnergyFund();
            return ApiUtils.success(resultList);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{energyId}")
    public ApiUtils.ApiResult<EnergyFundDetailDto> getEnergyDetail(@PathVariable String energyId){
        try{
            EnergyFundDetailDto result = energyFundDetailService.getEnergyFundDetail(energyId);
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/buy")
    public ApiUtils.ApiResult<Boolean> buyEnergy(@RequestBody BuyEnergyFundForm buyEnergyFundForm){
        try{
            Boolean result = energyFundService.buyEnergyFund(buyEnergyFundForm);
            if (!result){
                return ApiUtils.error("거래에 실패하였습니다.", HttpStatus.BAD_REQUEST);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/sell")
    public ApiUtils.ApiResult<Boolean> sellEnergy(@RequestBody BuyEnergyFundForm buyEnergyFundForm){
        try{
            Boolean result = energyFundService.sellEnergyFund(buyEnergyFundForm);
            if (result==false){
                return ApiUtils.error("판매에 실패하였습니다.", HttpStatus.BAD_REQUEST);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
