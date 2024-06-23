package donTouch.user_server.oauth.service;

import donTouch.user_server.oauth.OauthMemberClientComposite;
import donTouch.user_server.oauth.domain.AuthCodeRequestUrlProviderComposite;
import donTouch.user_server.oauth.domain.OauthMember;
import donTouch.user_server.oauth.domain.OauthMemberRepository;
import donTouch.user_server.oauth.domain.OauthServerType;
import donTouch.user_server.oauth.dto.LoginResponse;
import donTouch.user_server.user.domain.JpaUserRepository;
import donTouch.user_server.user.domain.Users;
import donTouch.user_server.user.dto.UsersDto;
import donTouch.user_server.user.utils.UsersMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OauthServiceImpl implements OauthService, UserDetailsService {
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UsersMapper usersMapper = UsersMapper.INSTANCE;

    @Override
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    @Override
    public UsersDto login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        Users isExist = jpaUserRepository.findByEmail(oauthMember.email())
                .orElseGet(()->{
                    Users newUser = Users.builder()
                            .email(oauthMember.email())
                            .nickname(oauthMember.nickname())
                            .snsType(oauthMember.snsType())
                            .investmentType(1)
                            .safeScore(65)
                            .dividendScore(30)
                            .growthScore(5)
                            .birthday(new Date())
                            .build();
                    jpaUserRepository.save(newUser);
                    return newUser;
                });
        return usersMapper.toDto(isExist);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Users> user = jpaUserRepository.findById(Long.valueOf(userId));
        return (UserDetails) user.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
