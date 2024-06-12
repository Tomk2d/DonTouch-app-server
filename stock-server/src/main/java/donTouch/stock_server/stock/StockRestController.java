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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
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
        List<StockDTO> result = stockService.findStocks(findStocksForm);

        if (result.isEmpty()) {
            return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ApiUtils.success(result);
    }

    @PostMapping("/detail")
    public ApiUtils.ApiResult<String> findStockDetail(@RequestBody @Valid FindStockDetailForm findStockDetailForm) {

    }

//    @PostMapping("/price")

}

