package donTouch.order_server.bankAccount.service;

import donTouch.order_server.bankAccount.domain.BankAccountLog;
import donTouch.order_server.bankAccount.domain.BankAccountLogJpaRepository;
import donTouch.order_server.bankAccount.dto.UserBankAccountLogDto;
import donTouch.order_server.kafka.dto.BankAccountLogDto;
import donTouch.order_server.utils.BankAccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public List<UserBankAccountLogDto> getUserBankAccountLog(Long userId) {
        List<BankAccountLog> bankAccountLogs = bankAccountLogRepository.findByUserId(userId);
        List<UserBankAccountLogDto> userBankAccountLogDtoList = new ArrayList<>();
        for(BankAccountLog bankAccountLog : bankAccountLogs){
            userBankAccountLogDtoList.add(bankAccountMapper.toUserDto(bankAccountLog));
        }
        Collections.sort(userBankAccountLogDtoList);
        return userBankAccountLogDtoList;
    }
}
