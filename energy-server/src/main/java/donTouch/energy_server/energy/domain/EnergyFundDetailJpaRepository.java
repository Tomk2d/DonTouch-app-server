package donTouch.energy_server.energy.domain;

import donTouch.energy_server.energy.dto.EnergyFundDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnergyFundDetailJpaRepository extends JpaRepository<EnergyFundDetail, Integer> {
//    @Query("SELECT new donTouch.energy_server.energy.dto.EnergyFundDetailDto( ef.energyId, ef.title, ef.titleImageUrl, ef.earningRate, ef.investmentPeriod, ef.fundingAmount, ef.sumOfInvestmentAndReservation, " +
//            "efd.borrowerInfo1Title, efd.borrowerInfo1Content, efd.borrowerInfo2Title, efd.borrowerInfo2Content, efd.borrowerInfo3Title, efd.borrowerInfo3Content, efd.fundUsage, efd.repaymentMethod, efd.earlyRepaymentFee, efd.grossReturnRate, efd.netReturnRate, efd.expectedTotalReturnRate, efd.projectName, efd.projectSite, efd.facilityCapacity, efd.contractor, efd.permit1, efd.permit2, efd.permit3, efd.permit4," +
//            "efd.projectOverviewImageLink1, efd.projectOverviewImageLink2, efd.projectOverviewImageLink3, efd.projectOverviewImageLink4, efd.repaymentSource1, efd.repaymentSource2, efd.collateralManagement1, efd.collateralManagement2, efd.collateralManagement3, " +
//            "efd.creditEnhancement1, efd.creditEnhancement2, efd.creditEnhancement3, efd.creditEnhancement4, efd.creditEnhancement5, efd.businessDescription, efd.creditRating, efd.financialStatus, efd.sameBorrowerLoanStatus, efd.collateralValue, efd.seniorDebt, efd.collateralList1, efd.collateralList2, efd.collateralList3, efd.collateralList4, efd.collateralList5, " +
//            "efd.collateralRecoveryValue1, efd.collateralRecoveryValue2, efd.collateralRecoveryValue3, efd.collateralRecoveryValue4) FROM EnergyFund ef JOIN EnergyFundDetail efd ON ef.id = efd.energyId WHERE ef.id = :energyId")
//    EnergyFundDetailDto findEnergyInfoById(@Param("energyId") String energyId);

    Optional<EnergyFundDetail> findEnergyInfoById(String energyId);
}
