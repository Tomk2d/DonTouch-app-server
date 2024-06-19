package donTouch.user_server.like.dto;

import donTouch.user_server.like.domain.LikeKrStock;
import donTouch.user_server.like.domain.LikeUsStock;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LikeStockForm {
    @NotNull(message = "input userId")
    private Long userId;

    @NotNull(message = "input exchange")
    private String exchange;

    @NotNull(message = "input stockId")
    private Integer stockId;

    public LikeKrStock convertToLikeKrStock() {
        return new LikeKrStock((long) 0, userId, stockId);
    }

    public LikeUsStock convertToLikeUsStock() {
        return new LikeUsStock((long) 0, userId, exchange, stockId);
    }
}
