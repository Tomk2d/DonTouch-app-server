package donTouch.stock_server.stock;

import donTouch.stock_server.kafka.service.KafkaService;
import donTouch.stock_server.stock.dto.FindStockDetailForm;
import donTouch.stock_server.stock.dto.FindStocksForm;
import donTouch.stock_server.stock.dto.StockDTO;
import donTouch.stock_server.stock.service.StockService;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping("/api/stocks")
public class StockRestController {
    private final KafkaService kafkaService;
    private final StockService stockService;

//    @PostMapping("/combination")
//    public ResponseEntity<ApiUtils.ApiResult> signUp(@Valid @RequestBody StockCombinationDto stockCombinationDto) {
//        // ID 중복 체크
//        isDuplicateId(signUpDto);
//        String savedMember = memberService.signUp(signUpDto);
//        return new ResponseEntity(success(savedMember), HttpStatus.CREATED);
//    }

    @PostMapping("")
    public ApiUtils.ApiResult<List<StockDTO>> findStocks(@RequestBody @Valid FindStocksForm findStocksForm) {
        List<StockDTO> stockDTOList = stockService.findStocks(findStocksForm);

        if (stockDTOList == null) {
            return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ApiUtils.success(stockDTOList);
    }

    @PostMapping("/detail")
    public ApiUtils.ApiResult<Map<String, Object>> findStockDetail(@RequestBody @Valid FindStockDetailForm findStockDetailForm) {
        try {
            Map<String, Object> stockDetail = stockService.findStockDetail(findStockDetailForm);

            if (stockDetail == null) {
                return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ApiUtils.success(stockDetail);

        } catch (InstanceNotFoundException e) {
            return ApiUtils.error("check id", HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/price")

}

