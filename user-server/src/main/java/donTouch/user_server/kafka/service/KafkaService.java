package donTouch.user_server.kafka.service;

import donTouch.user_server.kafka.dto.UsersDto;
import java.util.Date;
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

    public void sendResponse() {
        kafkaTemplate.send("user_response_test", new UsersDto(1L, "123@naver.com", 1,new Date(), "Tomkid", 1, 1, 1,1));
    }

    public void sendMessage(String topic, String groupId, Object data) {
        kafkaTemplate.send(topic, groupId, null);
    }


}
