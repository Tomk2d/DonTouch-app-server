package donTouch.user_server.user.service;

import donTouch.user_server.user.domain.BankAccount;
import donTouch.user_server.user.domain.BankAccountJpaRepository;
import donTouch.user_server.user.domain.JpaUserRepository;
import donTouch.user_server.user.domain.Users;
import donTouch.user_server.user.dto.BankAccountDto;
import donTouch.user_server.user.dto.BankCalculateForm;
import donTouch.user_server.user.dto.BankCreateForm;
import donTouch.user_server.user.utils.BankMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Transactional
@Slf4j
@AllArgsConstructor
@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountJpaRepository bankRepository;
    private final BankMapper bankMapper = BankMapper.INSTANCE;
    private final JpaUserRepository jpaUserRepository;


    @Override
    public BankAccountDto getBank(Long userId) {
        BankAccount bankAccount = bankRepository.findByUserId(userId)
            .orElseThrow(()-> new NullPointerException("no bank found this id"));
        return bankMapper.toDto(bankAccount);
    }

    @Override
    public String createBank(BankCreateForm bankCreateForm) {
        BankAccount form = getBankAccount(bankCreateForm);
        if (form == null) {
            throw new NullPointerException("유저가 없다.");
        }
        BankAccount result = bankRepository.save(form);
        if (result == null) {
            throw new NullPointerException("계좌 생성에 실패하였습니다.");
        }
        return "계좌가 성공적으로 생성되었습니다.";
    }

    @Override
    public BankAccountDto calculateMoney(BankCalculateForm bankCalculateForm) {
        BankAccount bank = bankRepository.findByUserId(bankCalculateForm.getUserId())
            .orElseThrow(()-> new NullPointerException("계좌를 찾을 수 없습니다."));
        Long price = bankCalculateForm.getPrice();
        Long cash = bank.getCash();

        if (price<0 && cash < Math.abs(price)){
            throw new NullPointerException("잔고가 부족합니다.");
        }else{
            bank.setCash(cash + price);
            BankAccount result = bankRepository.save(bank);

            return bankMapper.toDto(result);
        }
    }

    private BankAccount getBankAccount(BankCreateForm bankCreateForm) {
        Users user = jpaUserRepository.findById(bankCreateForm.getUserId())
            .orElseThrow(()-> new NullPointerException("유저가 없습니다."));
        BankAccount form = BankAccount.builder()
            .user(user)
            .cash(bankCreateForm.getCash())
            .build();
        return form;
    }

}
