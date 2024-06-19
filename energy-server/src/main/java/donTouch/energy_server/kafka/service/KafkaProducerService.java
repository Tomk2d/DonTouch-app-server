package donTouch.energy_server.kafka.service;

import donTouch.energy_server.kafka.dto.BankAccountLogDto;

import donTouch.energy_server.kafka.dto.HoldingEnergyFundForm;
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

    public void requestAddEnergy(HoldingEnergyFundForm holdingEnergyFundForm){
        kafkaTemplate.send("request_add_holding_energy", "order_group", holdingEnergyFundForm);
    }

    public void requestAddBankLog(BankAccountLogDto bankAccountLogDto) {
        kafkaTemplate.send("request_add_bank_account", "order_group", bankAccountLogDto);
    }
}
