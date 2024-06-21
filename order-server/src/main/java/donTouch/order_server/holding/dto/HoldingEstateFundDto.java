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
public class HoldingEstateFundDto {
    private int id;
    private Long userId;
    private int estateId;

    private String titleImageUrl;
    private String title;

    private double earningRate;
    private int investmentPeriod;
    private int inputCash;
    private LocalDateTime startPeriod;
    private LocalDateTime createdAt;
}

