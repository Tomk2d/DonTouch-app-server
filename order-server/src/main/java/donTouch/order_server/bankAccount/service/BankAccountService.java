package donTouch.order_server.bankAccount.service;

import donTouch.order_server.bankAccount.domain.BankAccountLog;
import donTouch.order_server.kafka.dto.BankAccountLogDto;

public interface BankAccountService {
    public void saveBankAccountLog(BankAccountLogDto bankAccountLogDto);


}
