package donTouch.estate_server.estate;

import donTouch.estate_server.estate.domain.EstateFund;
import donTouch.estate_server.estate.domain.EstateFundDetail;
import donTouch.estate_server.estate.dto.BuyEstateFundForm;
import donTouch.estate_server.estate.dto.EstateFundDetailDto;
import donTouch.estate_server.estate.service.EstateFundDetailService;
import donTouch.utils.utils.ApiUtils;
import donTouch.utils.utils.ApiUtils.ApiResult;
import donTouch.estate_server.estate.dto.EstateFundDto;
import donTouch.estate_server.estate.service.DataAccessService;
import donTouch.estate_server.estate.service.EstateFundService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class EstateRestController {
    private final EstateFundService estateFundService;
    private final EstateFundDetailService estateFundDetailService;
    private final DataAccessService dataAccessService;

//    @GetMapping("/dataInit")
//    public void initData(){
//        dataAccessService.saveEstate();
//    }

    @GetMapping("/api/estates")
    public ApiResult<List<EstateFundDto>> getAllEstates(){
        try{
            List<EstateFundDto> resultList = estateFundService.getAllEstateFund();
            return ApiUtils.success(resultList);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/estates/{estateId}")
    public ApiResult<EstateFundDetail> getEstate(@PathVariable int estateId){
        try{
            EstateFundDetail result = estateFundDetailService.findEstateInfoById(estateId);
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/estates/buy")
    public ApiResult<Boolean> buyEstate(@RequestBody BuyEstateFundForm buyEstateFundForm){
        try{
            Boolean result = estateFundService.buyEstateFund(buyEstateFundForm);
            if (!result){
                return ApiUtils.error("거래에 실패하였습니다.", HttpStatus.BAD_REQUEST);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/estates/sell")
    public ApiResult<Boolean> sellEstate(@RequestBody BuyEstateFundForm buyEstateFundForm){
        try{
            Boolean result = estateFundService.sellEstateFund(buyEstateFundForm);
            if (result==false){
                return ApiUtils.error("판매에 실패하였습니다.", HttpStatus.BAD_REQUEST);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
