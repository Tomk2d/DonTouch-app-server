package donTouch.stock_server.stock;

import donTouch.stock_server.kafka.service.KafkaService;
import donTouch.stock_server.stock.dto.FindStockForm;
import donTouch.stock_server.stock.dto.StockDTO;
import donTouch.stock_server.stock.service.StockService;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ApiUtils.ApiResult<List<StockDTO>> findStock(@RequestBody @Valid FindStockForm findStockForm) {
        List<StockDTO> result = stockService.findStock(findStockForm);

        if (result.isEmpty()) {
            return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ApiUtils.success(result);
    }

    @GetMapping("/{symbol}")
    public ApiUtils.ApiResult<String> findStockWithSymbol(@PathVariable String symbol) {
        return ApiUtils.success(symbol);
    }

}

