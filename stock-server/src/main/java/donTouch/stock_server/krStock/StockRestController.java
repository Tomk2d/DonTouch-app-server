package donTouch.stock_server.krStock;

import donTouch.stock_server.krStock.service.KafkaStockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class StockRestController {
    private final KafkaStockService kafkaStockService;

    @GetMapping("/kafkaTest2")
    public void kafkaTest() {
        kafkaStockService.sendRequest();
    }
}
