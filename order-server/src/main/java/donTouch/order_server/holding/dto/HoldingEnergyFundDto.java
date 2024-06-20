package donTouch.order_server.holding.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@Getter
public class HoldingEnergyFundDto {
    private int id;
    private Long userId;
    private String energyId;

    private String titleImageUrl;
    private String title;

    private double earningRate;
    private int investmentPeriod;
    private int inputCash;
    private LocalDate startPeriod;
    private LocalDate createdAt;
}

