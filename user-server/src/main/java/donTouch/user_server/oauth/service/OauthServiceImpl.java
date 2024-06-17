package donTouch.user_server.oauth.service;

import donTouch.user_server.oauth.OauthMemberClientComposite;
import donTouch.user_server.oauth.domain.AuthCodeRequestUrlProviderComposite;
import donTouch.user_server.oauth.domain.OauthMember;
import donTouch.user_server.oauth.domain.OauthMemberRepository;
import donTouch.user_server.oauth.domain.OauthServerType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OauthServiceImpl implements OauthService{
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    @Override
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    @Override
    public String login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        return saved.email();
    }
}
