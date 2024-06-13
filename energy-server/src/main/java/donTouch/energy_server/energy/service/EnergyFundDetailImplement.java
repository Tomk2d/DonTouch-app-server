package donTouch.energy_server.energy.service;

import donTouch.energy_server.energy.domain.EnergyFund;
import donTouch.energy_server.energy.domain.EnergyFundDetail;
import donTouch.energy_server.energy.domain.EnergyFundDetailJpaRepository;
import donTouch.energy_server.energy.domain.EnergyFundJpaRepository;
import donTouch.energy_server.energy.dto.EnergyFundDetailDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EnergyFundDetailImplement implements EnergyFundDetailService{

    private final EnergyFundJpaRepository energyRepository;
    private final EnergyFundDetailJpaRepository energyDetailRepository;
    @Override
    public EnergyFundDetailDto getEnergyFundDetail(String energyId) {
        Optional<EnergyFund> energyFundOptional = energyRepository.findById(energyId);
        Optional<EnergyFundDetail> energyFundDetailOptional = energyDetailRepository.findByEnergyId(energyId);

        if (energyFundOptional.isEmpty()) {
            throw new NullPointerException("energy fund not found : id = "+energyId);
        }
        if(energyFundDetailOptional.isEmpty()) {
            throw new NullPointerException("energy fund detail not found : id = " + energyId);
        }

        EnergyFund energyFund = energyFundOptional.get();
        EnergyFundDetail energyFundDetail = energyFundDetailOptional.get();

        return new EnergyFundDetailDto(
                energyFund.getId(),
                energyFund.getTitle(),
                energyFund.getTitleImageUrl(),
                energyFund.getEarningRate(),
                energyFund.getInvestment_period(),
                energyFund.getFundingAmount(),
                energyFund.getSumOfInvestmentAndReservation(),
                energyFundDetail.getBorrowerInfo1Title(),
                energyFundDetail.getBorrowerInfo1Content(),
                energyFundDetail.getBorrowerInfo2Title(),
                energyFundDetail.getBorrowerInfo2Content(),
                energyFundDetail.getBorrowerInfo3Title(),
                energyFundDetail.getBorrowerInfo3Content(),
                energyFundDetail.getFundUsage(),
                energyFundDetail.getRepaymentMethod(),
                energyFundDetail.getEarlyRepaymentFee(),
                energyFundDetail.getGrossReturnRate(),
                energyFundDetail.getNetReturnRate(),
                energyFundDetail.getExpectedTotalReturnRate(),
                energyFundDetail.getProjectName(),
                energyFundDetail.getProjectSite(),
                energyFundDetail.getFacilityCapacity(),
                energyFundDetail.getContractor(),
                energyFundDetail.getPermit1(),
                energyFundDetail.getPermit2(),
                energyFundDetail.getPermit3(),
                energyFundDetail.getPermit4(),
                energyFundDetail.getProjectOverviewImageLink1(),
                energyFundDetail.getProjectOverviewImageLink2(),
                energyFundDetail.getProjectOverviewImageLink3(),
                energyFundDetail.getProjectOverviewImageLink4(),
                energyFundDetail.getRepaymentSource1(),
                energyFundDetail.getRepaymentSource2(),
                energyFundDetail.getCollateralManagement1(),
                energyFundDetail.getCollateralManagement2(),
                energyFundDetail.getCollateralManagement3(),
                energyFundDetail.getCreditEnhancement1(),
                energyFundDetail.getCreditEnhancement2(),
                energyFundDetail.getCreditEnhancement3(),
                energyFundDetail.getCreditEnhancement4(),
                energyFundDetail.getCreditEnhancement5(),
                energyFundDetail.getBusinessDescription(),
                energyFundDetail.getCreditRating(),
                energyFundDetail.getFinancialStatus(),
                energyFundDetail.getSameBorrowerLoanStatus(),
                energyFundDetail.getCollateralValue(),
                energyFundDetail.getSeniorDebt(),
                energyFundDetail.getCollateralList1(),
                energyFundDetail.getCollateralList2(),
                energyFundDetail.getCollateralList3(),
                energyFundDetail.getCollateralList4(),
                energyFundDetail.getCollateralList5(),
                energyFundDetail.getCollateralRecoveryValue1(),
                energyFundDetail.getCollateralRecoveryValue2(),
                energyFundDetail.getCollateralRecoveryValue3(),
                energyFundDetail.getCollateralRecoveryValue4()
        );
    }
}
