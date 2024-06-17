package donTouch.user_server.oauth;

import donTouch.user_server.oauth.domain.OauthMember;
import donTouch.user_server.oauth.domain.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}
