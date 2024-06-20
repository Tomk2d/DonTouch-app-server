package donTouch.stock_server.stock;

import donTouch.stock_server.kafka.service.KafkaService;
import donTouch.stock_server.stock.dto.*;
import donTouch.stock_server.stock.service.StockService;
import donTouch.utils.exchangeRate.ExchangeRate;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping("/api/stocks")
public class StockRestController {
    private final KafkaService kafkaService;
    private final StockService stockService;

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
            return ApiUtils.error("stock detail is not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/chart")
    public ApiUtils.ApiResult<Map<String, Object>> findStockPrices(@Valid @RequestBody FindStockPricesForm findStockPricesForm) {
        try {
            Map<String, Object> stockPrices = stockService.findStockPrices(findStockPricesForm);

            if (stockPrices == null) {
                return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ApiUtils.success(stockPrices);

        } catch (InstanceNotFoundException e) {
            return ApiUtils.error("stock prices not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/combination/create")
    public ApiUtils.ApiResult<Map<String, Object>> findCombination(@Valid @RequestBody FindCombinationForm findCombinationForm) {
        try {
            Map<String, Object> combination = stockService.findCombination(findCombinationForm);

            if (combination == null) {
                return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ApiUtils.success(combination);
        } catch (NullPointerException e) {
            return ApiUtils.error("price not found", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/combination/distribute")
    public ApiUtils.ApiResult<Map<String, Object>> distributeCombination(@Valid @RequestBody DistributeCombinationForm distributeCombinationForm) {
        try {
            Map<String, Object> distribution = stockService.distributeCombination(distributeCombinationForm);

            if (distribution == null) {
                return ApiUtils.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ApiUtils.success(distribution);
        } catch (NullPointerException e) {
            return ApiUtils.error("price not found", HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return ApiUtils.error("combination should have 1 or more stocks", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/like")
    public ApiUtils.ApiResult<Map<String, Object>> findLikeStock(@RequestParam("userId") Integer userId) {
        WebClient webClient = WebClient.create();

        ResponseEntity<ApiUtils.ApiResult<List<LikeStockDTO>>> responseEntity = webClient.get()
                .uri("http://localhost:8081/api/user/like/stocks?userId=1")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<ApiUtils.ApiResult<List<LikeStockDTO>>>() {
                })
                .block();

        if (responseEntity == null || responseEntity.getBody() == null) {
            return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<LikeStockDTO> likeStockDTOList = responseEntity.getBody().getResponse();

        Map<String, Object> response = stockService.findLikeStocks(likeStockDTOList);

        if (response == null) {
            return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ApiUtils.success(response);
    }

    @GetMapping("/exchange/usd")
    public ApiUtils.ApiResult<ExchangeRateDTO> findExchangeRate() {
        ExchangeRate usd = ExchangeRate.USD;
        return ApiUtils.success(new ExchangeRateDTO(usd.getCurrency(), usd.getBuying(), usd.getSelling()));
    }
}

