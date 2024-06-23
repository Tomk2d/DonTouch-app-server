package donTouch.user_server.user.service;

import donTouch.user_server.kafka.dto.ChangeScoreDto;
import donTouch.user_server.user.dto.InvestmentTypeForm;
import donTouch.user_server.user.dto.UsersDto;

public interface UserService {
    UsersDto findUserByEmail(String email);

    UsersDto updateInvestmentType(InvestmentTypeForm investmentTypeForm);

    void changeScore(ChangeScoreDto changeScoreDto);
}
