package donTouch.estate_server.estate.service;

import donTouch.estate_server.estate.domain.EstateFund;
import donTouch.estate_server.estate.domain.EstateFundJpaRepository;
import donTouch.estate_server.estate.dto.BankCalculateForm;
import donTouch.estate_server.estate.dto.BuyEstateFundForm;
import donTouch.estate_server.estate.dto.EstateFundDto;
import donTouch.estate_server.estate.dto.HoldingEstateFundDto;
import donTouch.estate_server.estate.utils.EstateFundMapper;
import donTouch.estate_server.kafka.dto.BankAccountLogDto;
import donTouch.estate_server.kafka.dto.HoldingEstateFundForm;
import donTouch.estate_server.kafka.service.KafkaProducerService;
import donTouch.utils.utils.ApiUtils.ApiResult;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EstateFundServiceImpl implements EstateFundService {

    private final EstateFundJpaRepository estateFundRepository;
    private final EstateFundMapper estateFundMapper = EstateFundMapper.INSTANCE;
    private final RestTemplate restTemplate = new RestTemplate();
    private final KafkaProducerService kafkaProducerService;


    @Override
    public List<EstateFundDto> getAllEstateFund() {
        List<EstateFund> estateFundList = estateFundRepository.findAll();
        if (estateFundList.isEmpty()) {
            throw new NullPointerException("EstateFund List is empty");
        }
        List<EstateFundDto> estateFundDtoList = new ArrayList<>();
        estateFundList.forEach(estateFund -> {
            EstateFundDto dto = estateFundMapper.toDto(estateFund);
            estateFundDtoList.add(dto);
        });
        return estateFundDtoList;
    }


    @Override
    public Boolean buyEstateFund(BuyEstateFundForm buyEstateFundForm) {
        Long userId = buyEstateFundForm.getUserId();
        int estateFundId = buyEstateFundForm.getEstateFundId();
        int inputCash= buyEstateFundForm.getInputCash();
        String estateName = buyEstateFundForm.getEstateName();
        double estateEarningRate = buyEstateFundForm.getEstateEarningRate();

        EstateFund findedEstateFund = estateFundRepository.findById(estateFundId)
            .orElseThrow(()-> new NullPointerException("부동산 id 가 잘못되었습니다."));
        Long possibleInvest = findedEstateFund.getTotalAmountInvestments() - findedEstateFund.getCurrentInvest();

        if(possibleInvest < inputCash){
            throw new NullPointerException("투자 가능한 금액이 아닙니다.");
        }

        BankCalculateForm requestBody = new BankCalculateForm(buyEstateFundForm.getUserId(), (long) inputCash * -1);
        ApiResult result = restTemplate.postForEntity("http://localhost:8081/api/user/bank/cal", requestBody, ApiResult.class).getBody();
        System.out.println("result ======================== :" + result.getResponse());
        if(result.getResponse().equals("잔고가 부족합니다.")){
            throw new NullPointerException("잔고가 부족합니다.");
        }

        findedEstateFund.setCurrentInvest(findedEstateFund.getCurrentInvest() + (long) buyEstateFundForm.getInputCash());
        EstateFund savedEstateFund = estateFundRepository.save(findedEstateFund);
        System.out.println("현재 투자 금액 =================== " + savedEstateFund.getCurrentInvest());

        kafkaProducerService.requestAddEstate(new HoldingEstateFundForm(userId, estateFundId,inputCash,estateName, estateEarningRate));
        kafkaProducerService.requestAddBankLog(new BankAccountLogDto(userId, (long) inputCash, 1, estateName));
        return true;
    }

    @Override
    public Boolean sellEstateFund(BuyEstateFundForm buyEstateFundForm) {
        Long userId = buyEstateFundForm.getUserId();
        int estateFundId = buyEstateFundForm.getEstateFundId();
        int inputCash= buyEstateFundForm.getInputCash();
        String estateName = buyEstateFundForm.getEstateName();
        double estateEarningRate = buyEstateFundForm.getEstateEarningRate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HoldingEstateFundForm requestBody = new HoldingEstateFundForm(userId, estateFundId, inputCash, estateName, estateEarningRate);
        HttpEntity<HoldingEstateFundForm> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<HoldingEstateFundDto> responseEntity = restTemplate.postForEntity(
            "http://localhost:8085/api/holding/estate/sell",
            requestEntity,
            HoldingEstateFundDto.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            HoldingEstateFundDto responseBody = responseEntity.getBody();

            BankCalculateForm requestBodyBank = new BankCalculateForm(responseBody.getUserId(), (long) responseBody.getInputCash());
            ApiResult result = restTemplate.postForEntity("http://localhost:8081/api/user/bank/cal", requestBodyBank, ApiResult.class).getBody();
            if(result.getResponse().equals("잔고가 부족합니다.")){
                throw new NullPointerException("입금이 되지 않았습니다.");
            }
            kafkaProducerService.requestAddBankLog(new BankAccountLogDto(
                responseBody.getUserId(),
                (long) responseBody.getInputCash(), 0,
                responseBody.getEstateName())
            );
        } else {
            throw new NullPointerException("판매할 상품이 없습니다.");
        }



        return true;
    }
}
