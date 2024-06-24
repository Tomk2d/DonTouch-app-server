package donTouch.user_server.oauth;

import donTouch.user_server.oauth.config.NaverOauthConfig;
import donTouch.user_server.oauth.dto.NaverMemberResponse;
import donTouch.user_server.oauth.dto.NaverToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverApiClientImpl implements NaverApiClient{
    private final WebClient.Builder webClientBuilder;
    private final NaverOauthConfig naverOauthConfig;
    @Override
    public NaverToken fetchToken(MultiValueMap<String, String> params) {
        return null;
    }

    @Override
    public NaverMemberResponse fetchMember(String bearerToken) {
        WebClient webClient = webClientBuilder.baseUrl("https://openai.naver.com").build();

        try {
            Mono<NaverMemberResponse> responseMono = webClient.get()
                    .uri("/v1/nid/me")
                    .headers(headers -> {
                        headers.setBearerAuth(bearerToken);
                    })
                    .retrieve()
                    .bodyToMono(NaverMemberResponse.class);

            NaverMemberResponse response = responseMono.block();
            log.info("Fetched member info: {}", response);
            return response;

        } catch (WebClientResponseException e) {
            log.error("Error fetching member info: {}", e.getResponseBodyAsString());
            throw e;
        }
    }
}
