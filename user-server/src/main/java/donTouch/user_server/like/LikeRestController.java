package donTouch.user_server.like;

import donTouch.user_server.like.dto.LikeStockDTO;
import donTouch.user_server.like.dto.LikeStockForm;
import donTouch.user_server.like.service.LikeStockService;
import donTouch.utils.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping("/api/user/like")
public class LikeRestController {
    LikeStockService likeStockService;

    @PostMapping("/stocks")
    public ApiUtils.ApiResult<Object> likeStock(@RequestBody @Valid LikeStockForm likeStockForm) {
        LikeStockDTO savedlikeStockDTO = likeStockService.likeStock(likeStockForm);

        return ApiUtils.success(savedlikeStockDTO);
    }

    @GetMapping("/stocks")
    public ApiUtils.ApiResult<List<LikeStockDTO>> findLikeStocks(@RequestParam("userId") Long userId) {
        List<LikeStockDTO> likeStockDTOList = likeStockService.findLikeStocks(userId);

        if (likeStockDTOList == null) {
            return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ApiUtils.success(likeStockDTOList);
    }

    @DeleteMapping("/stocks")
    public ApiUtils.ApiResult<String> deleteStock(@RequestBody @Valid LikeStockForm likeStockForm) {
        Boolean isDeleted = likeStockService.dislikeStock(likeStockForm);

        if (isDeleted == null || !isDeleted) {
            return ApiUtils.error("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ApiUtils.success("deleted completely");
    }
}
