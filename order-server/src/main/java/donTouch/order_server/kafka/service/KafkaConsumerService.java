package donTouch.order_server.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import donTouch.order_server.bankAccount.service.BankAccountService;
import donTouch.order_server.holding.domain.HoldingKrStock;
import donTouch.order_server.holding.domain.HoldingUsStock;
import donTouch.order_server.holding.domain.KrStockDividendLog;
import donTouch.order_server.holding.domain.UsStockDividendLog;
import donTouch.order_server.holding.dto.HoldingEnergyFundForm;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;
import donTouch.order_server.holding.dto.HoldingKrStockDto;
import donTouch.order_server.holding.dto.HoldingUsStockDto;
import donTouch.order_server.holding.dto.KrStockDividendLogDto;
import donTouch.order_server.holding.dto.KrStockTradingLogDto;
import donTouch.order_server.holding.dto.UsStockDividendLogDto;
import donTouch.order_server.holding.dto.UsStockTradingLogDto;
import donTouch.order_server.holding.service.HoldingEnergyFundService;
import donTouch.order_server.holding.service.HoldingEstateFundService;
import donTouch.order_server.holding.service.HoldingKrStockService;
import donTouch.order_server.holding.service.HoldingUsStockService;
import donTouch.order_server.holding.service.KrStockDividendService;
import donTouch.order_server.holding.service.KrStockTradingLogService;
import donTouch.order_server.holding.dto.HoldingEnergyFundForm;
import donTouch.order_server.holding.service.HoldingEnergyFundService;
import donTouch.order_server.holding.service.UsStockDividendLogService;
import donTouch.order_server.holding.service.UsStockTradingLogService;
import donTouch.order_server.kafka.dto.BankAccountLogDto;
import donTouch.order_server.kafka.dto.CombinationStockForm;
import donTouch.order_server.kafka.dto.CompleteStockForm;
import java.sql.Date;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@AllArgsConstructor
@EnableKafka
@Service
public class KafkaConsumerService {
    private KafkaTemplate<String, Object> kafkaTemplate;
    private final HoldingEstateFundService holdingEstateFundService;
    private final HoldingEnergyFundService holdingEnergyFundService;
    private final BankAccountService bankAccountService;
    private final HoldingKrStockService holdingKrStockService;
    private final KrStockTradingLogService krStockTradingLogService;
    private final KrStockDividendService krStockDividendService;
    private final HoldingUsStockService holdingUsStockService;
    private final UsStockTradingLogService usStockTradingLogService;
    private final UsStockDividendLogService usStockDividendLogService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "response_calculate_bank", groupId = "order_group")
    public Boolean getCalculateResponse(Boolean isSuccess) {
        return isSuccess;
    }

    @KafkaListener(topics = "request_add_holding_estate", groupId = "order_group")
    public void saveHoldingEstate(HoldingEstateFundForm data) {
        System.out.println(data.getEstateId() + data.getUserId() + data.getTitle());
        holdingEstateFundService.saveEstate(data);
    }

    @KafkaListener(topics = "request_add_holding_energy", groupId = "order_group")
    public void saveHoldingEnergy(HoldingEnergyFundForm data) {
        System.out.println(data.getEnergyId() + data.getUserId() + data.getTitle());
        holdingEnergyFundService.saveEnergy(data);
    }

    @KafkaListener(topics = "request_add_bank_account", groupId = "order_group")
    public void saveBankAccountLog(BankAccountLogDto data) {
        bankAccountService.saveBankAccountLog(data);
    }

    @KafkaListener(topics = "request_complete_stock", groupId = "order_group")
    public void saveHoldingKrStock(ConsumerRecord<String, Object> record) {
        Object value = record.value();
        if (value instanceof LinkedHashMap) {
            CompleteStockForm data = objectMapper.convertValue(value, CompleteStockForm.class);
            HoldingKrStock savedHolding = holdingKrStockService.save(new HoldingKrStockDto(data.getUserId(), data.getKrHoldingStockId(), data.getKrStockAmount()));
            krStockTradingLogService.save(new KrStockTradingLogDto(data.getUserId(), data.getKrHoldingStockId(), savedHolding.getId(), data.getKrStockPrice(), data.getKrStockAmount(), 0, 1));
            System.out.println("주식수량 들어와 ?????????? "+data.getKrStockAmount());
            bankAccountService.saveBankAccountLog(new BankAccountLogDto(data.getUserId(),data.getInOutCash(), data.getInOutType(), data.getStockName(), LocalDateTime.now()));

        }
    }

    @KafkaListener(topics = "request_complete_stock_sell", groupId = "order_group")
    public void saveHoldingKrStockSell(ConsumerRecord<String, Object> record) {
        Object value = record.value();
        if (value instanceof LinkedHashMap) {
            CompleteStockForm data = objectMapper.convertValue(value, CompleteStockForm.class);
            HoldingKrStock savedHolding = holdingKrStockService.findHolding(data.getUserId(), data.getKrHoldingStockId());
            krStockTradingLogService.save(new KrStockTradingLogDto(data.getUserId(), data.getKrHoldingStockId(), savedHolding.getId(), data.getKrStockPrice(), data.getKrStockAmount(), 0, 0));
            System.out.println("주식수량 들어와 ?????????? "+data.getKrStockAmount());
            bankAccountService.saveBankAccountLog(new BankAccountLogDto(data.getUserId(),data.getInOutCash(), data.getInOutType(), data.getStockName(), LocalDateTime.now()));

        }
    }

    @KafkaListener(topics = "request_complete_stock_us", groupId = "order_group")
    public void saveHoldingUsStock(ConsumerRecord<String, Object> record) {
        Object value = record.value();
        if (value instanceof LinkedHashMap) {
            CompleteStockForm data = objectMapper.convertValue(value, CompleteStockForm.class);
            HoldingUsStock savedHolding = holdingUsStockService.save(new HoldingUsStockDto(data.getUserId(), data.getKrHoldingStockId(), data.getKrStockAmount()));
            usStockTradingLogService.save(new UsStockTradingLogDto(data.getUserId(), data.getKrHoldingStockId(), savedHolding.getId(), data.getKrStockPrice(), data.getKrStockAmount(), 0, 1));
            System.out.println("주식수량 들어와 ?????????? "+data.getKrStockAmount());
            bankAccountService.saveBankAccountLog(new BankAccountLogDto(data.getUserId(),data.getInOutCash(), data.getInOutType(), data.getStockName(), LocalDateTime.now()));
        }
    }
    @KafkaListener(topics = "request_complete_stock_sell_us", groupId = "order_group")
    public void saveHoldingUsStockSell(ConsumerRecord<String, Object> record) {
        Object value = record.value();
        if (value instanceof LinkedHashMap) {
            CompleteStockForm data = objectMapper.convertValue(value, CompleteStockForm.class);
            HoldingUsStock savedHolding = holdingUsStockService.findHolding(data.getUserId(), data.getKrHoldingStockId());
            usStockTradingLogService.save(new UsStockTradingLogDto(data.getUserId(), data.getKrHoldingStockId(), savedHolding.getId(), data.getKrStockPrice(), data.getKrStockAmount(), 0, 0));
            System.out.println("주식수량 들어와 ?????????? "+data.getKrStockAmount());
            bankAccountService.saveBankAccountLog(new BankAccountLogDto(data.getUserId(),data.getInOutCash(), data.getInOutType(), data.getStockName(), LocalDateTime.now()));

        }
    }
    @KafkaListener(topics = "request_buy_combination_stock", groupId = "order_group")
    public void saveDividendKrStock(ConsumerRecord<String, Object> record) {
        Object value = record.value();
        if (value instanceof LinkedHashMap) {
            CombinationStockForm data = objectMapper.convertValue(value, CombinationStockForm.class);
            HoldingKrStock savedHolding = holdingKrStockService.save(new HoldingKrStockDto(data.getUserId(), data.getKrHoldingStockId(), data.getKrStockAmount()));
            KrStockDividendLog result = krStockDividendService.save(new KrStockDividendLogDto(data.getCombinationId(), data.getUserId(), LocalDateTime.now()));
            krStockTradingLogService.save(new KrStockTradingLogDto(data.getUserId(), data.getKrHoldingStockId(), savedHolding.getId(), data.getKrStockPrice(), data.getKrStockAmount(), result.getId(), 1));
            System.out.println("주식수량 들어와 ?????????? "+data.getKrStockAmount());
            bankAccountService.saveBankAccountLog(new BankAccountLogDto(data.getUserId(),data.getInOutCash(), data.getInOutType(), data.getStockName(), LocalDateTime.now()));
        }
    }
    @KafkaListener(topics = "request_buy_combination_stock_us", groupId = "order_group")
    public void saveDividendUsStock(ConsumerRecord<String, Object> record) {
        Object value = record.value();
        if (value instanceof LinkedHashMap) {
            CombinationStockForm data = objectMapper.convertValue(value, CombinationStockForm.class);
            HoldingUsStock savedHolding = holdingUsStockService.save(new HoldingUsStockDto(data.getUserId(), data.getKrHoldingStockId(), data.getKrStockAmount()));
            UsStockDividendLog result = usStockDividendLogService.save(new UsStockDividendLogDto(data.getCombinationId(), data.getUserId(), LocalDateTime.now()));
            usStockTradingLogService.save(new UsStockTradingLogDto(data.getUserId(), data.getKrHoldingStockId(), savedHolding.getId(), data.getKrStockPrice(), data.getKrStockAmount(), result.getId(), 1));
            System.out.println("주식수량 들어와 ?????????? "+data.getKrStockAmount());
            bankAccountService.saveBankAccountLog(new BankAccountLogDto(data.getUserId(),data.getInOutCash(), data.getInOutType(), data.getStockName(), LocalDateTime.now()));
        }
    }
}
