package donTouch.stock_server.krStock;

import donTouch.stock_server.kafka.service.KafkaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stocks")
public class StockKrRestController {
    private final KafkaService kafkaService;

    @PostMapping("/combination")
    public ResponseEntity<ApiUtils.ApiResult> signUp(@Valid @RequestBody StockCombinationDto stockCombinationDto) {
        // ID 중복 체크
        isDuplicateId(signUpDto);
        String savedMember = memberService.signUp(signUpDto);
        return new ResponseEntity(success(savedMember), HttpStatus.CREATED);
    }


}

