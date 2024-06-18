package donTouch.energy_server.energy.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EnergyFundDto {
    @NotBlank(message = "energyId가 빈칸입니다.")
    private String energyId;
    @NotBlank(message = "title이 빈칸입니다.")
    private String title;
    @NotBlank(message = "titleImageUrl이 빈칸입니다.")
    private String titleImageUrl;

    @NotNull(message = "earningRate가 null입니다.")
    private Double earningRate;
    @NotNull(message = "investment_period가 null입니다.")
    private int investment_period;
    @NotNull(message = "fundingAmount가 null입니다.")
    private Double fundingAmount;
    @NotNull(message = "sumOfInvestmentAndReservation가 null 입니다.")
    private int sumOfInvestmentAndReservation;
    @NotNull(message = "crditRating이 null 입니다.")
    private String creditRating;
}
