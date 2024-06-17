package donTouch.user_server.oauth;

import donTouch.user_server.oauth.config.KakaoOauthConfig;
import donTouch.user_server.oauth.domain.OauthMember;
import donTouch.user_server.oauth.domain.OauthServerType;
import donTouch.user_server.oauth.dto.KakaoMemberResponse;
import donTouch.user_server.oauth.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient{
    private final KakaoApiClientImpl kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public OauthMember fetch(String authCode) {
        MultiValueMap<String, String> params = tokenRequestParams(authCode);
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(params);
        KakaoMemberResponse kakaoMemberResponse =
                kakaoApiClient.fetchMember(tokenInfo.accessToken());
        return kakaoMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.clientSecret());

        return params;
    }
}
