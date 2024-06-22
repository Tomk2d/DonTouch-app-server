package donTouch.order_server.holding.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

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
