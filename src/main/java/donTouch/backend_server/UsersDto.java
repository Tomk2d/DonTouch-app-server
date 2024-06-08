package donTouch.backend_server;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.AllArgsConstructor;

@JsonSerialize
@AllArgsConstructor
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
}
