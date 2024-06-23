package donTouch.order_server.kafka.service;

import donTouch.order_server.kafka.dto.TradingStockInfoDto;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class KafkaProducerService {
    private KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void requestStockInfoToChangeUserScore(TradingStockInfoDto tradingStockInfoDTO) {
        kafkaTemplate.send("request_stock_info_to_change_user_score", "stock_group", tradingStockInfoDTO);
        System.out.println("sent kafka");
    }
}
