package donTouch.user_server.kafka.service;

import donTouch.user_server.kafka.dto.ChangeScoreDto;
import donTouch.user_server.kafka.dto.IsSuccessDto;
import donTouch.user_server.kafka.dto.UsersDto;
import donTouch.user_server.user.dto.BankAccountDto;
import donTouch.user_server.user.dto.BankCalculateForm;
import donTouch.user_server.user.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class KafkaService {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private final BankAccountService bankAccountService;

    @Autowired
    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate,
                        BankAccountService bankAccountService) {
        this.kafkaTemplate = kafkaTemplate;
        this.bankAccountService = bankAccountService;
    }

    public void sendResponse() {
        kafkaTemplate.send("user_response_test", new UsersDto(1L, "123@naver.com", 1, new Date(), "Tomkid", 1, 1, 1, 1));
    }

    @KafkaListener(topics = "request_calculate_bank")
    @SendTo("response_calculate_bank")
    public IsSuccessDto calculateBank(BankCalculateForm bankCalculateForm) {
        // 은행에서 거래 요청 받음
        BankAccountDto result = bankAccountService.calculateMoney(bankCalculateForm);
        if (result == null) {
            IsSuccessDto isSuccessDto = new IsSuccessDto(false);
            return isSuccessDto;
        }
        IsSuccessDto isSuccessDto = new IsSuccessDto(true);
        return isSuccessDto;
    }

    @KafkaListener(topics = "request_to_change_user_score", groupId = "user_group")
    public void responseToChangeUserScore(ChangeScoreDto changeScoreDto) {
        System.out.println("get response: " + changeScoreDto.getUserId().toString() + ", " + changeScoreDto.getHighestScoreOfPurchasedStock() + ", " + changeScoreDto.getLowestScoreOfPurchasedStock());
    }

    public void sendCalBankResponse(Boolean isSuccess) {
        kafkaTemplate.send("response_calculate_bank", isSuccess);
    }

    public void sendMessage(String topic, String groupId, Object data) {
        kafkaTemplate.send(topic, groupId, null);
    }
}
