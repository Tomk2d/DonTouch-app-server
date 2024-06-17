package donTouch.user_server.oauth.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "oauth_user")
public class OauthMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @Embedded
//    private OauthId oauthId;
    private String nickname;
    //private String profileImageUrl;
    private String email;
    private Integer snsType;


    public String nickname() {
        return nickname;
    }

    public String email() {
        return email;
    }
    public Integer snsType() {
        return snsType;
    }
}
