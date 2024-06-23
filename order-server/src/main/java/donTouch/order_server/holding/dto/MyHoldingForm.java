package donTouch.order_server.holding.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyHoldingForm {
    @NotNull
    private Long userId;
    @NotNull
    private String stockCode;
    @NotNull
    private int stockPrice;
    @NotNull
    private int stockAmount;
}
