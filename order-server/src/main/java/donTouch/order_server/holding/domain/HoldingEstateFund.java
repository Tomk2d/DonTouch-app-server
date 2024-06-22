package donTouch.order_server.holding.domain;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@Table(name="HoldingEstateFund")
public class HoldingEstateFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Long userId;

    @NotNull
    private int estateId;

    @NotNull
    private String titleImageUrl;

    @NotNull
    private String title;

    @NotNull
    private double earningRate;

    @NotNull
    private int investmentPeriod;

    @NotNull
    @Min(5000)
    @Max(5000000)
    private int inputCash;

    @NotNull
    private LocalDateTime startPeriod;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
