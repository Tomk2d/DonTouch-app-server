package donTouch.user_server.oauth.service;

import donTouch.user_server.oauth.domain.OauthServerType;
import donTouch.user_server.oauth.dto.LoginResponse;

public interface OauthService {
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType);
    public LoginResponse login(OauthServerType oauthServerType, String authCode);
}
