package donTouch.estate_server.estate;

import donTouch.utils.utils.ApiUtils;
import donTouch.utils.utils.ApiUtils.ApiResult;
import donTouch.estate_server.estate.dto.EstateFundDto;
import donTouch.estate_server.estate.service.DataAccessService;
import donTouch.estate_server.estate.service.EstateFundService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class EstateRestController {
    private final DataAccessService dataAccessService;
    private final EstateFundService estateFundService;

    @GetMapping("/saveEstate")
    public String saveEstate() {
        return dataAccessService.saveEstate();
    }

    @GetMapping("/api/estates")
    public ApiResult<List<EstateFundDto>> getAllEstates(){
        try{
            List<EstateFundDto> resultList = estateFundService.getAllEstateFund();
            return ApiUtils.success(resultList);
        }catch (NullPointerException e){
            return ApiUtils.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
