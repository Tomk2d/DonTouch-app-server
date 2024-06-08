package donTouch.energy_server.kafka.service;

import donTouch.energy_server.kafka.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "user_response_test",groupId = "stock_group")
    public void getResponse(UsersDto data){
        log.info("이게 가져온 데이터 : " + data.toString());
    }
}
