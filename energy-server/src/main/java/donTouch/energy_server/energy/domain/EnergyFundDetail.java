package donTouch.energy_server.energy.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EnergyFundDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String energyId;

    private String borrowerInfo1Title;
    private String borrowerInfo1Content;
    private String borrowerInfo2Title;
    private String borrowerInfo2Content;
    private String borrowerInfo_3Title;
    private String borrowerInfo_3Content;

    private Integer investmentPeriod;
    private Double fundingAmount;
    private String fundUsage;
    private Double totalInvestmentAmount;
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
    private String sameBorrowLoanStatus;
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
