package donTouch.estate_server.kafka.service;

import donTouch.estate_server.kafka.dto.BankAccountLogDto;
import donTouch.estate_server.kafka.dto.HoldingEstateFundForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaProducerService {
    private KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void requestAddEstate(HoldingEstateFundForm holdingEstateFundForm){
        kafkaTemplate.send("request_add_holding_estate", "order_group", holdingEstateFundForm);
    }

    public void requestAddBankLog(BankAccountLogDto bankAccountLogDto) {
        kafkaTemplate.send("request_add_bank_account", "order_group", bankAccountLogDto);
    }
}
