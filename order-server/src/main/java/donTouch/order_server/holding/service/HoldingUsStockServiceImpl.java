package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingUsStock;
import donTouch.order_server.holding.domain.HoldingUsStockJpaRepository;
import donTouch.order_server.holding.domain.UsStockTradingLog;
import donTouch.order_server.holding.domain.UsStockTradingLogJpaRepository;
import donTouch.order_server.holding.dto.HoldingUsStockDto;
import donTouch.order_server.holding.dto.HoldingUsStockFindForm;
import donTouch.order_server.holding.dto.PurchaseInfoDTO;
import donTouch.order_server.kafka.dto.TradingStockInfoDto;
import donTouch.order_server.kafka.service.KafkaProducerService;
import donTouch.order_server.utils.UsStockMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HoldingUsStockServiceImpl implements HoldingUsStockService {
    HoldingUsStockJpaRepository holdingUsStockJpaRepository;
    private final UsStockMapper usStockMapper = UsStockMapper.INSTANCE;
    UsStockTradingLogJpaRepository usStockTradingLogJpaRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public List<String> findHoldingStockIds(Long userId) {
        List<HoldingUsStock> holdingKrStocks = holdingUsStockJpaRepository.findAllByUserId(userId);

        return holdingKrStocks.stream()
                .map(HoldingUsStock::getUsStockId)
                .collect(Collectors.toList());
    }

    @Override
    public HoldingUsStock save(HoldingUsStockDto holdingUsStockDto) {
        Long userId = holdingUsStockDto.getUserId();
        String usStockId = holdingUsStockDto.getUsStockId();
        int orderAmount = holdingUsStockDto.getUsStockAmount();
        HoldingUsStock entity = usStockMapper.toEntity(holdingUsStockDto);

        Optional<HoldingUsStock> findHolding = holdingUsStockJpaRepository.findByUserIdAndUsStockId(userId, usStockId);
        if (findHolding.isPresent()) {
            HoldingUsStock findEntity = findHolding.get();
            findEntity.setUsStockAmount(findEntity.getUsStockAmount() + orderAmount);
            return holdingUsStockJpaRepository.save(findEntity);
        } else {
            TradingStockInfoDto tradingStockInfoDto = holdingUsStockDto.convertToTradingStockInfoDTO();
            kafkaProducerService.requestStockInfoToChangeUserScore(tradingStockInfoDto);

            return holdingUsStockJpaRepository.save(entity);
        }
    }

    @Override
    public HoldingUsStock findHolding(Long userId, String krHoldingStockId) {
        return holdingUsStockJpaRepository.findByUserIdAndUsStockId(userId, krHoldingStockId)
                .orElseThrow(() -> new NullPointerException("해당 주식을 보유하고 있지 않습니다."));
    }

    @Override
    public HoldingUsStock sellStockUpdate(HoldingUsStockFindForm holdingUsStockFindForm) {
        Long userId = holdingUsStockFindForm.getUserId();
        String usStockId = holdingUsStockFindForm.getUsStockId();
        HoldingUsStock result = findHolding(userId, usStockId);

        int myAmount = result.getUsStockAmount();
        int orderAmount = holdingUsStockFindForm.getUsStockAmount();

        if (myAmount > orderAmount) {
            result.setUsStockAmount(myAmount - orderAmount);
            HoldingUsStock updateHolding = holdingUsStockJpaRepository.save(result);
            return updateHolding;
        } else if (myAmount == orderAmount) {
            holdingUsStockJpaRepository.delete(result);
            result.setUsStockAmount(myAmount - orderAmount);
            return result;
        } else {
            throw new NullPointerException("주식 보유수량이 주문 수량보다 적습니다.");
        }
    }

    @Override
    public List<PurchaseInfoDTO> findHoldingStockInfos(Long userId, List<String> symbols) {
        List<UsStockTradingLog> usStockTradingLogList = usStockTradingLogJpaRepository.findAllByUserIdAndUsStockIdIn(userId, symbols);

        Map<String, PurchaseInfoDTO> tradedUsStocks = new HashMap<>();
        for (UsStockTradingLog tradingLog : usStockTradingLogList) {
            tradedUsStocks.putIfAbsent(tradingLog.getUsStockId(), new PurchaseInfoDTO(tradingLog.getUsStockId(), (long) 0, (long) 0));

            PurchaseInfoDTO purchaseInfoDTO = tradedUsStocks.get(tradingLog.getUsStockId());
            if (tradingLog.getTradingType() == 1) {
                purchaseInfoDTO.increaseTotalPurchasePrice((long) tradingLog.getUsStockBuyPrice() * tradingLog.getUsStockBuyAmount());
                purchaseInfoDTO.increaseQuantity((long) tradingLog.getUsStockBuyAmount());
                continue;
            }
            purchaseInfoDTO.decreaseTotalPurchasePrice((long) tradingLog.getUsStockBuyPrice() * tradingLog.getUsStockBuyAmount());
            purchaseInfoDTO.decreaseQuantity((long) tradingLog.getUsStockBuyAmount());
        }

        return new ArrayList<>(tradedUsStocks.values());
    }
}
