package donTouch.energy_server.energy.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "energy_fund")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EnergyFund {

    @Id
    private String energyId;

    private String title;
    private String titleImageUrl;

    private Double earningRate;
    private Integer investmentPeriod;
    private Double fundingAmount;

    private Integer sumOfInvestmentAndReservation;
    private String creditRating;
}
