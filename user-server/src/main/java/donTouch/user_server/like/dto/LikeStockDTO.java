package donTouch.user_server.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeStockDTO {
    private String exchange;
    private Integer stockId;
}
