package donTouch.energy_server.energy.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Builder
@AllArgsConstructor
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
    private LocalDateTime startPeriod;
}

