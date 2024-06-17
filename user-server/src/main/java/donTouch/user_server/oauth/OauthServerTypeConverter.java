package donTouch.user_server.oauth;

import donTouch.user_server.oauth.domain.OauthServerType;
import org.springframework.core.convert.converter.Converter;

public class OauthServerTypeConverter implements Converter<String, OauthServerType>{
    @Override
    public OauthServerType convert(String source) {
        return OauthServerType.fromName(source);
    }
}
