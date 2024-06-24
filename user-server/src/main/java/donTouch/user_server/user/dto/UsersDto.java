package donTouch.user_server.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import donTouch.user_server.user.domain.Users;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@ToString
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
@AllArgsConstructor
public class UsersDto {
    private Long id;
    @Email
    @NotBlank(message = "이메일이 없습니다.")
    private String email;
    @NotNull
    @Size(min=1, max=3, message = "sns 타입 확인하세요.")
    private Integer snsType;
    @DateTimeFormat
    @NotBlank(message = "출생년도가 없습니다.")
    private Date birthday;
    @NotBlank(message = "닉네임이 없습니다.")
    private String nickname;
    @Size(min=1, max=5, message = "투자 타입 확인하세요.")
    private Integer investmentType;
    @Size(min=0, max = 150, message = "스코어 확인하세요.")
    private Integer safeScore;
    @Size(min=0, max = 150, message = "스코어 확인하세요.")
    private Integer dividendScore;
    @Size(min=0, max = 150, message = "스코어 확인하세요.")
    private Integer growthScore;

    public Users toEntity(){
        return Users.builder()
            .email(this.email)
            .snsType(this.snsType)
            .birthday(this.birthday)
            .nickname(this.nickname)
            .investmentType(this.investmentType)
            .safeScore(this.safeScore)
            .dividendScore(this.dividendScore)
            .growthScore(this.growthScore)
            .build();
    }
    public static UsersDto toDto(Users users){
        return new UsersDto(
                users.getId(),
            users.getEmail(),
            users.getSnsType(),
            users.getBirthday(),
            users.getNickname(),
            users.getInvestmentType(),
            users.getSafeScore(),
            users.getDividendScore(),
            users.getGrowthScore()
        );
    }
}
