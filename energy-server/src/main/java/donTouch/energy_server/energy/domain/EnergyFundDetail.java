package donTouch.energy_server.energy.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
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

    @Column(name = "borrower_info_1_title")
    private String borrowerInfo1Title;
    @Column(name = "borrower_info_1_content")
    private String borrowerInfo1Content;
    @Column(name = "borrower_info_2_title")
    private String borrowerInfo2Title;
    @Column(name = "borrower_info_2_content")
    private String borrowerInfo2Content;
    @Column(name = "borrower_info_3_title")
    private String borrowerInfo3Title;
    @Column(name = "borrower_info_3_content")
    private String borrowerInfo3Content;

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

    @Column(name = "permit_1")
    private String permit1;
    @Column(name = "permit_2")
    private String permit2;
    @Column(name = "permit_3")
    private String permit3;
    @Column(name = "permit_4")
    private String permit4;

    @Column(name = "project_overview_image_link_1")
    private String projectOverviewImageLink1;
    @Column(name = "project_overview_image_link_2")
    private String projectOverviewImageLink2;
    @Column(name = "project_overview_image_link_3")
    private String projectOverviewImageLink3;
    @Column(name = "project_overview_image_link_4")
    private String projectOverviewImageLink4;

    @Column(name = "repayment_source_1")
    private String repaymentSource1;
    @Column(name = "repayment_source_2")
    private String repaymentSource2;
    @Column(name = "collateral_management_1")
    private String collateralManagement1;
    @Column(name = "collateral_management_2")
    private String collateralManagement2;
    @Column(name = "collateral_management_3")
    private String collateralManagement3;

    @Column(name = "credit_enhancement_1")
    private String creditEnhancement1;
    @Column(name = "credit_enhancement_2")
    private String creditEnhancement2;
    @Column(name = "credit_enhancement_3")
    private String creditEnhancement3;
    @Column(name = "credit_enhancement_4")
    private String creditEnhancement4;
    @Column(name = "credit_enhancement_5")
    private String creditEnhancement5;

    private String businessDescription;
    private String creditRating;
    private String financialStatus;
    private String sameBorrowerLoanStatus;
    private String collateralValue;
    private String seniorDebt;

    @Column(name = "collateral_list_1")
    private String collateralList1;
    @Column(name = "collateral_list_2")
    private String collateralList2;
    @Column(name = "collateral_list_3")
    private String collateralList3;
    @Column(name = "collateral_list_4")
    private String collateralList4;
    @Column(name = "collateral_list_5")
    private String collateralList5;

    @Column(name = "collateral_recovery_value_1")
    private String collateralRecoveryValue1;
    @Column(name = "collateral_recovery_value_2")
    private String collateralRecoveryValue2;
    @Column(name = "collateral_recovery_value_3")
    private String collateralRecoveryValue3;
    @Column(name = "collateral_recovery_value_4")
    private String collateralRecoveryValue4;

}
