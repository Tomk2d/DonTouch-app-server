package donTouch.user_server.like;

import donTouch.user_server.like.dto.LikeStockDTO;
import donTouch.user_server.like.dto.LikeStockForm;
import donTouch.user_server.like.service.LikeStockService;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping("/api/user")
public class LikeRestController {
    LikeStockService likeStockService;

    @PostMapping("/like/stocks/create")
    public ApiUtils.ApiResult<Object> likeStock(@RequestBody @Valid LikeStockForm likeStockForm) {
        if (likeStockForm.getExchange().equals("KSC")) {
            LikeStockDTO savedStock = likeStockService.likeKrStock(likeStockForm);

            if (savedStock == null) {
                return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ApiUtils.success(savedStock);
        }

        LikeStockDTO savedStock = likeStockService.likeUsStock(likeStockForm);

        if (savedStock == null) {
            return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ApiUtils.success(savedStock);
    }

    @GetMapping("/like/stocks/{userId}")
    public ApiUtils.ApiResult<Map<String, Object>> findLikeStocks(@PathVariable Long userId) {
        Map<String, Object> likeStockDTOList = likeStockService.findLikeStocks(userId);

        if (likeStockDTOList == null) {
            return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ApiUtils.success(likeStockDTOList);
    }
}
