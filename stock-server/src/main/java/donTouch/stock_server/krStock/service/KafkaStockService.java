package donTouch.stock_server.krStock.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaStockService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaStockService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRequest(){
        kafkaTemplate.send("user_request", "hello");
    }
    @KafkaListener(topics = "user_response",groupId = "stock_group")
    public void getResponse(String data){
        log.info("이게 가져온 데이터 : " + data);
    }
}
