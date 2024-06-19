package donTouch.energy_server.energy;

import donTouch.energy_server.energy.dto.BuyEnergyFundForm;
import donTouch.energy_server.energy.dto.EnergyFundDetailDto;
import donTouch.energy_server.energy.dto.EnergyFundDto;
import donTouch.energy_server.energy.service.EnergyFundDetailService;
import donTouch.energy_server.energy.service.EnergyFundService;
import donTouch.utils.utils.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class EnergyRestController {
    private EnergyFundService energyFundService;
    private EnergyFundDetailService energyFundDetailService;

    @GetMapping("/api/energy")
    public ApiUtils.ApiResult<List<EnergyFundDto>> getAllEnergy(){
        try{
            List<EnergyFundDto> resultList = energyFundService.getAllEnergyFund();
            return ApiUtils.success(resultList);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/energy/{energyId}")
    public ApiUtils.ApiResult<EnergyFundDetailDto> getEnergyDetail(@PathVariable String energyId){
        try{
            EnergyFundDetailDto result = energyFundDetailService.getEnergyFundDetail(energyId);
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/energy/buy")
    public ApiUtils.ApiResult<Boolean> buyEstate(@RequestBody BuyEnergyFundForm buyEnergyFundForm){
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
    @PostMapping("/api/energy/sell")
    public ApiUtils.ApiResult<Boolean> sellEstate(@RequestBody BuyEnergyFundForm buyEnergyFundForm){
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
