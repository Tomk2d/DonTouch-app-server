package donTouch.user_server.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.user_server.oauth.domain.OauthMember;

import static donTouch.user_server.oauth.domain.OauthServerType.NAVER;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record NaverMemberResponse(
        String resultcode,
        String message,
        Response response
) {
    public OauthMember toDomain() {
        return OauthMember.builder()
                //.oauthId(new OauthId(String.valueOf(response.id), NAVER))
                .nickname(response.nickname)
                //.profileImageUrl(response.profileImage)
                .snsType(2)
                .build();
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profileImage,
            String birthyear,
            String mobile
    ) {
    }
}
