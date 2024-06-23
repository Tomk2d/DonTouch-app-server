package donTouch.user_server.user.service;

import donTouch.user_server.kafka.dto.ChangeScoreDto;
import donTouch.user_server.user.domain.JpaUserRepository;
import donTouch.user_server.user.domain.Users;
import donTouch.user_server.user.dto.InvestmentTypeForm;
import donTouch.user_server.user.dto.UsersDto;
import donTouch.user_server.user.utils.UsersMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final JpaUserRepository jpaUserRepository;
    private final UsersMapper usersMapper = UsersMapper.INSTANCE;

    public UsersDto findUserByEmail(String email) {
        Users user = jpaUserRepository.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("User not found"));
        return UsersDto.toDto(user);
    }


    public UsersDto updateInvestmentType(InvestmentTypeForm investmentTypeForm) {
        Users user = jpaUserRepository.findById(investmentTypeForm.getUserId())
                .orElseThrow(() -> new NullPointerException("유저 정보가 없습니다."));

        Integer score = investmentTypeForm.getTotalScore();
        int result;
        int safeScore;
        int growthScore;
        int dividendScore;

        if (score < 0) {
            throw new NullPointerException("점수는 0 또는 양수로 입력하세요.");
        } else if (score <= 7) {
            result = 1; // 안정형
            safeScore = 65;
            growthScore = 5;
            dividendScore = 30;
        } else if (score <= 14) {
            result = 2; // 안정추구형
            safeScore = 50;
            growthScore = 20;
            dividendScore = 30;
        } else if (score <= 21) {
            result = 3; // 위험중립형
            safeScore = 35;
            growthScore = 35;
            dividendScore = 30;
        } else if (score <= 28) {
            result = 4; // 적극투자형
            safeScore = 20;
            growthScore = 50;
            dividendScore = 30;
        } else {
            result = 5; //공격투자형
            safeScore = 5;
            growthScore = 65;
            dividendScore = 30;
        }

        user.setInvestmentType(result);
        user.setSafeScore(safeScore);
        user.setGrowthScore(growthScore);
        user.setDividendScore(dividendScore);
        jpaUserRepository.save(user);

        return usersMapper.toDto(user);
    }

    @Override
    public void changeScore(ChangeScoreDto changeScoreDto) {
        int scoreToChange = 4;

        Optional<Users> user = jpaUserRepository.findById(changeScoreDto.getUserId());
        if (user.isEmpty()) {
            log.error("can not find user");
            return;
        }

        Users userToChange = user.get();

        Map<String, Integer> score = new HashMap<>();
        score.put("safeScore", user.get().getSafeScore());
        score.put("growthScore", user.get().getGrowthScore());
        score.put("dividendScore", user.get().getDividendScore());

        score.compute(changeScoreDto.getHighestScoreOfPurchasedStock(), (key, val) -> val != null ? val + scoreToChange : null);
        score.compute(changeScoreDto.getLowestScoreOfPurchasedStock(), (key, val) -> val != null ? val - scoreToChange : null);

        userToChange.setScores(score.get("safeScore"), score.get("growthScore"), score.get("dividendScore"));

        jpaUserRepository.save(userToChange);
    }
}
