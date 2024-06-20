package donTouch.energy_server.utils;

import donTouch.utils.utils.ApiUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class Web {
    public static List<String> getLikeEnergyFundIds(Long userId) {
        try {
            WebClient webClient = WebClient.create();

            String getLikeEnergyFundIdsUrl = "http://localhost:8081/api/user/like/energy?userId=" + userId;
            ResponseEntity<ApiUtils.ApiResult<List<String>>> responseEntity = webClient.get()
                    .uri(getLikeEnergyFundIdsUrl)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiUtils.ApiResult<List<String>>>() {
                    })
                    .block();

            return responseEntity.getBody().getResponse();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
