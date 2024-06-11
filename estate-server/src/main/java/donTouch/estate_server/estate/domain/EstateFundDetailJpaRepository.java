package donTouch.estate_server.estate.domain;

import donTouch.estate_server.estate.dto.EstateFundDetailDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface EstateFundDetailJpaRepository extends JpaRepository<EstateFundDetail, Integer> {
    @Query("SELECT new donTouch.estate_server.estate.dto.EstateFundDetailDto(f.id, f.title, f.length, f.titleMainImageUrl, f.earningRate, f.totalAmountInvestments, f.sumOfInvestmentAndReservation, f.loanAmountBaseLtv, d.estateId, d.latitude, d.longitude, d.appraisedValue, d.priorityAmount, d.etcAmount, d.leftMoney, d.bidWinningRate, d.mortgageSetupRate, d.expectedRecoverAmount, d.priorityMaximumPledgeAmount, d.maxBondAmountBaseLtv, d.shareRatio, d.loanType, d.finalAmountWon, d.finalLength, d.workPeriodYears, d.judgeCompanySize, d.userSex, d.method, d.isAffirmedToPurchase, d.principalReturnAmountWon, d.interestReturnAmountWon, d.workStartDate, d.repaymentDay, d.isRenewalLoan, d.isRepaymentDayFollowingExecutionDay, d.comment, d.startDatetime, d.state, d.amount, d.investmentCount, d.photos, d.reason, d.progress, d.structure, d.eightCreditGrade, d.sellingPointsIconImage, d.sellingPointsTitle, d.sellingPointsDescription, d.sellingPointsIconImage2, d.sellingPointsTitle2, d.sellingPointsDescription2, d.expertName, d.expertRole, d.expertContent, d.hasOverdueLast1Year, d.complianceNumber, d.dealType, d.category) " +
        "FROM EstateFund f JOIN EstateFundDetail d ON f.id = d.estateId WHERE f.id = :id")
    EstateFundDetailDto findEstateInfoById(@Param("id") int id);

}
