package donTouch.user_server.oauth.service;

import donTouch.user_server.oauth.OauthMemberClientComposite;
import donTouch.user_server.oauth.domain.AuthCodeRequestUrlProviderComposite;
import donTouch.user_server.oauth.domain.OauthMember;
import donTouch.user_server.oauth.domain.OauthMemberRepository;
import donTouch.user_server.oauth.domain.OauthServerType;
import donTouch.user_server.oauth.dto.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
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
    public LoginResponse login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByEmail(oauthMember.email())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setNickname(saved.nickname());
        loginResponse.setEmail(saved.email());
        loginResponse.setSnsType(saved.snsType());

        return loginResponse;
    }
}
