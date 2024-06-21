package donTouch.estate_server.estate.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EstateFundDetailDto {
    private Integer id;
    private String title;
    private Integer length;
    private String titleMainImageUrl;
    private Double earningRate;
    private Integer totalAmountInvestments;
    private Double sumOfInvestmentAndReservation;
    private Double loanAmountBaseLtv;
    private Long currentInvest;
    private Integer estateId;
    private Double latitude;
    private Double longitude;
    private Integer appraisedValue;
    private Integer priorityAmount;
    private Integer etcAmount;
    private Integer leftMoney;
    private Double bidWinningRate;
    private Integer mortgageSetupRate;
    private Integer expectedRecoverAmount;
    private Integer priorityMaximumPledgeAmount;
    private Double maxBondAmountBaseLtv;
    private Double shareRatio;
    private String loanType;
    private Integer finalAmountWon;
    private Integer finalLength;
    private Double workPeriodYears;
    private String judgeCompanySize;
    private Integer userSex;
    private String method;
    private Boolean isAffirmedToPurchase;
    private Integer principalReturnAmountWon;
    private Integer interestReturnAmountWon;
    private LocalDateTime workStartDate;
    private Integer repaymentDay;
    private Boolean isRenewalLoan;
    private Boolean isRepaymentDayFollowingExecutionDay;
    private String comment;
    private LocalDateTime startDatetime;
    private String state;
    private Integer amount;
    private Integer investmentCount;
    private String photos;
    private String reason;
    private Integer progress;
    private String structure;
    private String eightCreditGrade;
    private String sellingPointsIconImage;
    private String sellingPointsTitle;
    private String sellingPointsDescription;
    private String sellingPointsIconImage2;
    private String sellingPointsTitle2;
    private String sellingPointsDescription2;
    private String expertName;
    private String expertRole;
    private String expertContent;
    private Boolean hasOverdueLast1Year;
    private String complianceNumber;
    private String dealType;
    private String category;

}

