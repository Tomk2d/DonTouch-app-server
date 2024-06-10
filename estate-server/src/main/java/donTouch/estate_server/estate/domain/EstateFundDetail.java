package donTouch.estate_server.estate.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.estate_server.estate.utils.StringToDoubleDeserializer;
import donTouch.estate_server.estate.utils.StringToIntDeserializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class EstateFundDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int estateId;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private double latitude;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private double longitude;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int appraisedValue;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int priorityAmount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int etcAmount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int leftMoney;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int bidWinningRate;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int mortgageSetupRate;

    private String mortgageDescription;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int expectedRecoverAmount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int priorityMaximumPledgeAmount;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private float maxBondAmountBaseLtv;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private float shareRatio;

    private String loanType;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int finalAmountWon;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int finalLength;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private float workPeriodYears;

    private String judgeCompanySize;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int userSex;

    private String method;

    private boolean isAffirmedToPurchase;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int principalReturnAmountWon;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int interestReturnAmountWon;

    private Date workStartDate;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int repaymentDay;

    private boolean isRenewalLoan;

    private boolean isRepaymentDayFollowingExecutionDay;

    private String comment;

    private Date startDatetime;

    private String state;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int amount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int investmentCount;

    private String photos;

    private String reason;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int progress;

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

    private boolean hasOverdueLast1Year;

    private String complianceNumber;

    private String dealType;

    private String category;
}