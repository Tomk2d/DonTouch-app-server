package donTouch.order_server.holding.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.order_server.holding.dto.PurchasedStockDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class UsStockTradingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Long userId;
    @NotNull
    private String usStockId;
    @NotNull
    private Long usHoldingStockId;
    @NotNull
    private int usStockBuyPrice;
    @NotNull
    private int usStockBuyAmount;
    @NotNull
    private LocalDateTime usStockBuyTime;
    @Nullable
    private int combination;
    @NotNull
    private int tradingType;

    @PrePersist
    protected void onCreate() {
        if (this.usStockBuyTime == null) {
            this.usStockBuyTime = LocalDateTime.now();
        }
    }

    public PurchasedStockDTO convertToPurchasedStockDTO() {
        return new PurchasedStockDTO(usStockBuyTime, combination,
                "us", usStockId, usStockBuyPrice, usStockBuyAmount);
    }
}

