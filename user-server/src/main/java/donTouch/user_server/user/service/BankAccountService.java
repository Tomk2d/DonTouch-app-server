package donTouch.user_server.user.service;

import donTouch.user_server.user.dto.BankAccountDto;
import donTouch.user_server.user.dto.BankCalculateForm;
import donTouch.user_server.user.dto.BankCreateForm;

public interface BankAccountService {

    BankAccountDto getBank(Long userId);
    String createBank(BankCreateForm bankCreateForm);

    BankAccountDto calculateMoney(BankCalculateForm bankCalculateForm);
    BankAccountDto calculateAccountMoney(BankCalculateForm bankCalculateForm);
}
