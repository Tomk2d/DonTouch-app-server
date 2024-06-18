package donTouch.user_server.oauth.domain;

import donTouch.user_server.oauth.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {
    OauthServerType supportServer();
    String provide();
}
