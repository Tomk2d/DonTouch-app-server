package donTouch.estate_server.utils;

import donTouch.utils.utils.ApiUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class Web {
    public static List<Integer> getLikeEstateFundIds(Long userId) {
        try {
            WebClient webClient = WebClient.create();

            String getLikeEstateFundIdsUrl = "http://localhost:8081/api/user/like/estate?userId=" + userId;
            ResponseEntity<ApiUtils.ApiResult<List<Integer>>> responseEntity = webClient.get()
                    .uri(getLikeEstateFundIdsUrl)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiUtils.ApiResult<List<Integer>>>() {
                    })
                    .block();

            return responseEntity.getBody().getResponse();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
