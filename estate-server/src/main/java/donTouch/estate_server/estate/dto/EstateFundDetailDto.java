package donTouch.estate_server.estate.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstateFundDetailDto {
    private int id;
    private String title;
    private int length;
    private String titleMainImageUrl;
    private Double earningRate;
    private int totalAmountInvestments;
    private Double sumOfInvestmentAndReservation;
    private Double loanAmountBaseLtv;
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
    private Date workStartDate;
    private Integer repaymentDay;
    private Boolean isRenewalLoan;
    private Boolean isRepaymentDayFollowingExecutionDay;
    private String comment;
    private Date startDatetime;
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

