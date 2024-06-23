package donTouch.stock_server.kafka.service;

import donTouch.stock_server.kafka.dto.ChangeScoreDto;
import donTouch.stock_server.kafka.dto.TradingStockInfoDto;
import donTouch.stock_server.kafka.dto.UsersDto;
import donTouch.stock_server.stock.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final StockService stockService;

    @KafkaListener(topics = "user_response_test", groupId = "stock_group")
    public void getResponse(UsersDto data) {
        log.info("이게 가져온 데이터 : " + data.toString());
    }

    @KafkaListener(topics = "request_stock_info_to_change_user_score", groupId = "stock_group")
    public void responseStockInfoToChangeUserScore(TradingStockInfoDto tradingStockInfoDto) {
        ChangeScoreDto changeScoreDto = stockService.requestToChangeUserScore(tradingStockInfoDto);
        if (changeScoreDto != null) {
            kafkaTemplate.send("request_to_change_user_score", "user_group", changeScoreDto);
        }
    }
}
