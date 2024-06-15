package donTouch.order_server.bankAccount.service;

import donTouch.order_server.bankAccount.domain.BankAccountLog;
import donTouch.order_server.bankAccount.domain.BankAccountLogJpaRepository;
import donTouch.order_server.kafka.dto.BankAccountLogDto;
import donTouch.order_server.utils.BankAccountMapper;
import donTouch.order_server.utils.EstateFundMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountLogJpaRepository bankAccountLogRepository;
    private final BankAccountMapper bankAccountMapper = BankAccountMapper.INSTANCE;


    @Override
    public void saveBankAccountLog(BankAccountLogDto bankAccountLogDto) {
        BankAccountLog bankAccountLog = bankAccountMapper.toEntity(bankAccountLogDto);
        bankAccountLogRepository.save(bankAccountLog);
    }
}
