package donTouch.user_server.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaUserService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaUserService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics="user_request",groupId = "stock_group")
    public void sendResponse() {
        kafkaTemplate.send("user_response", "이건 유저야.");
        log.info("전송완료");
    }
}
