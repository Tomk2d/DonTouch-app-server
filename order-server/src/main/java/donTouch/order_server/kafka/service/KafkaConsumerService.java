package donTouch.order_server.kafka.service;

import donTouch.order_server.bankAccount.service.BankAccountService;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;
import donTouch.order_server.holding.service.HoldingEstateFundService;
import donTouch.order_server.kafka.dto.BankAccountLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class KafkaConsumerService {
    private KafkaTemplate<String, Object> kafkaTemplate;
    private final HoldingEstateFundService holdingEstateFundService;
    private final BankAccountService bankAccountService;

    @Autowired
    public KafkaConsumerService(KafkaTemplate<String, Object> kafkaTemplate,
        HoldingEstateFundService holdingEstateFundService, BankAccountService bankAccountService) {
        this.kafkaTemplate = kafkaTemplate;
        this.holdingEstateFundService = holdingEstateFundService;
        this.bankAccountService = bankAccountService;
    }

    @KafkaListener(topics="response_calculate_bank", groupId = "order_group")
    public Boolean getCalculateResponse(Boolean isSuccess){
        return isSuccess;
    }

    @KafkaListener(topics = "request_add_holding_estate", groupId = "order_group")
    public void saveHoldingEstate(HoldingEstateFundForm data){
        System.out.println("here I am");
        holdingEstateFundService.saveEstate(data);
    }
    @KafkaListener(topics = "request_add_bank_account", groupId = "order_group")
    public void saveBankAccountLog(BankAccountLogDto data){
        System.out.println("잘들어옴 =========" + data +"=========");
        bankAccountService.saveBankAccountLog(data);
    }

}
