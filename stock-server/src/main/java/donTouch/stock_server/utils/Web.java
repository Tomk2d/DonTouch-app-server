package donTouch.stock_server.utils;

import donTouch.utils.utils.ApiUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class Web {
    public static <T> T getResponse(String url) {
        WebClient webClient = WebClient.create();

        ResponseEntity<ApiUtils.ApiResult<T>> responseEntity = webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<ApiUtils.ApiResult<T>>() {
                })
                .block();

        if (responseEntity == null || responseEntity.getBody() == null) {
            return null;
        }
        return responseEntity.getBody().getResponse();
    }
}
