package donTouch.user_server.userStock;

import donTouch.user_server.kafka.service.KafkaService;
import donTouch.utils.Exceptions.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserStockRestController {
  private final KafkaService kafkaService;

  @GetMapping("/api/user/stocks")
  public ApiUtils.ApiResult<Object> getStocks() {
    try {
      kafkaService.sendMessage("request_kr_stocks", "stock_group", null);
      return ApiUtils.success("sent kafka message");
    }
    catch(Exception e) {
      return ApiUtils.error("fail to send kafka message", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
