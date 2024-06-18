package donTouch.user_server.oauth;

import donTouch.user_server.oauth.config.KakaoOauthConfig;
import donTouch.user_server.oauth.dto.KakaoMemberResponse;
import donTouch.user_server.oauth.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoApiClientImpl implements KakaoApiClient {
    private final WebClient.Builder webClientBuilder;
    private final KakaoOauthConfig kakaoOauthConfig;
    @Override
    public KakaoToken fetchToken(MultiValueMap<String, String> params) {
        WebClient webClient = webClientBuilder.baseUrl("https://kauth.kakao.com").build();

        Mono<KakaoToken> responseMono = webClient.post()
                .uri("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(params))
                .retrieve()
                .bodyToMono(KakaoToken.class);

        log.info(responseMono.toString());

        KakaoToken response = responseMono.block();
        log.info("Fetched token info: {}", response);
        return response;
    }

    @Override
    public KakaoMemberResponse fetchMember(String bearerToken) {
        WebClient webClient = webClientBuilder.baseUrl("https://kapi.kakao.com").build();

        try {
            Mono<KakaoMemberResponse> responseMono = webClient.get()
                    .uri("/v2/user/me")
                    .headers(headers -> {
                        headers.setBearerAuth(bearerToken);
                    })
                    .retrieve()
                    .bodyToMono(KakaoMemberResponse.class);

            KakaoMemberResponse response = responseMono.block();
            log.info("Fetched member info: {}", response);
            return response;

        } catch (WebClientResponseException e) {
            log.error("Error fetching member info: {}", e.getResponseBodyAsString());
            throw e;
        }
    }
}
