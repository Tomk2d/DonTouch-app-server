package donTouch.order_server.kafka.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
public class UsersDto {
    private Long id;
    private String email;
    private Integer snsType;
    private Date birthday;
    private String nickname;
    private Integer investmentType;
    private Integer safeScore;
    private Integer dividendScore;
    private Integer growthScore;

    public UsersDto() {
    }

    public UsersDto(Long id, String email, Integer snsType, Date birthday, String nickname,
        Integer investmentType, Integer safeScore, Integer dividendScore, Integer growthScore) {
        this.id = id;
        this.email = email;
        this.snsType = snsType;
        this.birthday = birthday;
        this.nickname = nickname;
        this.investmentType = investmentType;
        this.safeScore = safeScore;
        this.dividendScore = dividendScore;
        this.growthScore = growthScore;
    }
}
