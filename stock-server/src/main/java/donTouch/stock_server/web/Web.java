package donTouch.stock_server.web;

import donTouch.stock_server.web.dto.LikeStockDTO;
import donTouch.stock_server.web.dto.PurchaseInfoDTO;
import donTouch.utils.utils.ApiUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

public class Web {
    public static List<LikeStockDTO> getLikeStockDTOList(Long userId) {
        try {
            WebClient webClient = WebClient.create();

            String getLikeStockIdsUrl = "http://localhost:8081/api/user/like/stocks?userId=";
            ResponseEntity<ApiUtils.ApiResult<List<LikeStockDTO>>> responseEntity = webClient.get()
                    .uri(getLikeStockIdsUrl + userId)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiUtils.ApiResult<List<LikeStockDTO>>>() {
                    })
                    .block();

            return responseEntity.getBody().getResponse();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public static Map<String, List<String>> getHoldingSymbols(Long userId) {
        try {
            WebClient webClient = WebClient.create();

            String getLikeStockIdsUrl = "http://localhost:8085/api/holding/stocks?&userId=" + userId + "&getPrice=false";

            ResponseEntity<ApiUtils.ApiResult<Map<String, List<String>>>> responseEntity = webClient.get()
                    .uri(getLikeStockIdsUrl)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiUtils.ApiResult<Map<String, List<String>>>>() {
                    })
                    .block();

            return responseEntity.getBody().getResponse();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public static Map<String, List<PurchaseInfoDTO>> getHoldingStockPurchaseInfos(Long userId) {
        try {
            WebClient webClient = WebClient.create();

            String getLikeStockIdsUrl = "http://localhost:8085/api/holding/stocks?&userId=" + userId + "&getPrice=true";

            ResponseEntity<ApiUtils.ApiResult<Map<String, List<PurchaseInfoDTO>>>> responseEntity = webClient.get()
                    .uri(getLikeStockIdsUrl)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiUtils.ApiResult<Map<String, List<PurchaseInfoDTO>>>>() {
                    })
                    .block();

            return responseEntity.getBody().getResponse();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
