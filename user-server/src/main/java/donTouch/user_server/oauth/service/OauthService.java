package donTouch.user_server.oauth.service;

import donTouch.user_server.oauth.domain.OauthServerType;
import donTouch.user_server.oauth.dto.LoginResponse;
import donTouch.user_server.user.dto.UsersDto;

public interface OauthService {
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType);
    public UsersDto login(OauthServerType oauthServerType, String authCode);
}
