package donTouch.user_server.like.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindLikeStockForm {
    @NotNull(message = "input userId")
    private Long userId;
}
