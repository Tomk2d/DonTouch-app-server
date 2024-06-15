package donTouch.user_server.user;

import donTouch.user_server.kafka.service.KafkaService;
import donTouch.user_server.user.dto.BankAccountDto;
import donTouch.user_server.user.dto.BankCalculateForm;
import donTouch.user_server.user.dto.BankCreateForm;
import donTouch.user_server.user.dto.UsersDto;
import donTouch.user_server.user.service.BankAccountService;
import donTouch.user_server.user.service.UserService;
import donTouch.utils.utils.ApiUtils;
import donTouch.utils.utils.ApiUtils.ApiResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final KafkaService kafkaService;

    @GetMapping("/test")
    public void test() {
        kafkaService.sendResponse();
    }

    @GetMapping("/api/user/{email}")
    public UsersDto getUser(@PathVariable String email) {
        try {
            return userService.findUserByEmail(email);
        }catch(NullPointerException e) {
            return null;
        }
    }
    @GetMapping("/api/user/bank/{userId}")
    public ApiResult<BankAccountDto> getBank(@PathVariable Long userId) {
        try{
            BankAccountDto result = bankAccountService.getBank(userId);
            return ApiUtils.success(result);
        }catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/user/bank/new")
    public ApiResult<String> createBankAccount(@RequestBody @Valid BankCreateForm bankCreateForm) {
        try{
            String result = bankAccountService.createBank(bankCreateForm);
            return ApiUtils.success(result);
        }catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/user/bank/cal")
    public ApiResult<BankAccountDto> calBankAccount(@RequestBody @Valid BankCalculateForm bankCalculateForm) {
        try{
            BankAccountDto result = bankAccountService.calculateMoney(bankCalculateForm);
            if (result == null){
                return ApiUtils.error("잔고가 부족합니다.", HttpStatus.BAD_REQUEST);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e) {
            return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
