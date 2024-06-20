package donTouch.user_server.user;

import donTouch.user_server.kafka.service.KafkaService;
import donTouch.user_server.oauth.domain.OauthMember;
import donTouch.user_server.oauth.domain.OauthServerType;
import donTouch.user_server.oauth.dto.LoginResponse;
import donTouch.user_server.user.dto.BankAccountDto;
import donTouch.user_server.user.dto.BankCalculateForm;
import donTouch.user_server.user.dto.BankCreateForm;
import donTouch.user_server.user.dto.UsersDto;
import donTouch.user_server.user.service.BankAccountService;
import donTouch.user_server.oauth.service.OauthService;
import donTouch.user_server.user.service.UserService;
import donTouch.utils.utils.ApiUtils;
import donTouch.utils.utils.ApiUtils.ApiResult;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@Slf4j
public class UserRestController {
    private final OauthService oauthService;
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

    @PostMapping("/api/user/account/cal")
    public ApiResult<BankAccountDto> calBankUserAccount(@RequestBody @Valid BankCalculateForm bankCalculateForm) {
        try{
            BankAccountDto result = bankAccountService.calculateAccountMoney(bankCalculateForm);
            if (result == null){
                return ApiUtils.error("잔고가 부족합니다.", HttpStatus.BAD_REQUEST);
            }
            return ApiUtils.success(result);
        }catch (NullPointerException e) {
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

    @GetMapping("/api/user/oauth/login/{oauthServerType}")
    public ApiResult<LoginResponse> login(
            @PathVariable OauthServerType oauthServerType,
            @RequestParam("code") String code
    ) {
        LoginResponse loginUser = oauthService.login(oauthServerType, code);
        return ApiUtils.success(loginUser);
    }

    @SneakyThrows
    @GetMapping("/api/user/oauth/{oauthServerType}")
    public ApiResult<String> redirectAuthCodeRequestUrl(
            @PathVariable OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);

        log.info(redirectUrl);
        response.sendRedirect(redirectUrl);
        return ApiUtils.success("토큰 요청 성공");
    }
}
