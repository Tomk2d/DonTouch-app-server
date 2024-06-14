package donTouch.energy_server.energy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EnergyFundDetailDto {

    @NotBlank(message = "energyId가 빈칸입니다.")
    private String energyId;
    @NotBlank(message = "title이 빈칸입니다.")
    private String title;
    @NotBlank(message = "titleImageUrl이 빈칸입니다.")
    private String titleImageUrl;

    @NotNull(message = "earningRate가 null입니다.")
    private Double earningRate;
    @NotNull(message = "investment_period가 null입니다.")
    private int investmentPeriod;
    @NotNull(message = "fundingAmount가 null입니다.")
    private Double fundingAmount;
    @NotNull(message = "sumOfInvestmentAndReservation 가 null 입니다.")
    private int sumOfInvestmentAndReservation;

    private String borrowerInfo1Title;
    private String borrowerInfo1Content;
    private String borrowerInfo2Title;
    private String borrowerInfo2Content;
    private String borrowerInfo3Title;
    private String borrowerInfo3Content;

    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;

    private String fundUsage;
    private String repaymentMethod;
    private String earlyRepaymentFee;

    private Integer grossReturnRate;
    private Double netReturnRate;
    private Double expectedTotalReturnRate;

    private String projectName;
    private String projectSite;
    private String facilityCapacity;
    private String contractor;

    private String permit1;
    private String permit2;
    private String permit3;
    private String permit4;

    private String projectOverviewImageLink1;
    private String projectOverviewImageLink2;
    private String projectOverviewImageLink3;
    private String projectOverviewImageLink4;

    private String repaymentSource1;
    private String repaymentSource2;
    private String collateralManagement1;
    private String collateralManagement2;
    private String collateralManagement3;

    private String creditEnhancement1;
    private String creditEnhancement2;
    private String creditEnhancement3;
    private String creditEnhancement4;
    private String creditEnhancement5;

    private String businessDescription;
    private String creditRating;
    private String financialStatus;
    private String sameBorrowerLoanStatus;
    private String collateralValue;
    private String seniorDebt;

    private String collateralList1;
    private String collateralList2;
    private String collateralList3;
    private String collateralList4;
    private String collateralList5;

    private String collateralRecoveryValue1;
    private String collateralRecoveryValue2;
    private String collateralRecoveryValue3;
    private String collateralRecoveryValue4;
}