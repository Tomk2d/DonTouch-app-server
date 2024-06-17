package donTouch.user_server.oauth.service;

import donTouch.user_server.oauth.domain.OauthServerType;

public interface OauthService {
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType);
    public String login(OauthServerType oauthServerType, String authCode);
}
