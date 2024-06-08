package donTouch.stock_server.krStock;

import donTouch.stock_server.kafka.service.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class StockKrRestController {
    private final KafkaService kafkaService;
}

