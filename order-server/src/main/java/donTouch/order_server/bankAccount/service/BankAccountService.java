package donTouch.order_server.bankAccount.service;

import donTouch.order_server.bankAccount.dto.UserBankAccountLogDto;
import donTouch.order_server.kafka.dto.BankAccountLogDto;

import java.util.List;

public interface BankAccountService {
    public void saveBankAccountLog(BankAccountLogDto bankAccountLogDto);

    public List<UserBankAccountLogDto> getUserBankAccountLog(Long userId);
}
