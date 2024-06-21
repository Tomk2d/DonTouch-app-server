package donTouch.order_server.holding.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.order_server.holding.dto.PurchasedStockDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class KrStockTradingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Long userId;
    @NotNull
    private String krStockId;
    @NotNull
    private Long krHoldingStockId;
    @NotNull
    private int krStockBuyPrice;
    @NotNull
    private int krStockBuyAmount;
    @NotNull
    private LocalDate krStockBuyTime;
    @Nullable
    private int combination;
    @NotNull
    private int tradingType;

    public PurchasedStockDTO convertToPurchasedStockDTO() {
        return new PurchasedStockDTO(krStockBuyTime, combination,
                "kr", krStockId, krStockBuyPrice, krStockBuyAmount);
    }
}
