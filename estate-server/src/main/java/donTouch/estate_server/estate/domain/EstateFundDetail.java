package donTouch.estate_server.estate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.estate_server.estate.utils.StringToDoubleDeserializer;
import donTouch.estate_server.estate.utils.StringToIntDeserializer;
import jakarta.persistence.Column;
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
    private Integer id;

    @JsonProperty("estateId")
    private Integer estateId;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    @JsonProperty("latitude")
    private Double latitude;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    @JsonProperty("longitude")
    private Double longitude;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("appraisedValue")
    private Integer appraisedValue;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("priorityAmount")
    private Integer priorityAmount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("etcAmount")
    private Integer etcAmount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("leftMoney")
    private Integer leftMoney;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    @JsonProperty("bidWinningRate")
    private Double bidWinningRate;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("mortgageSetupRate")
    private Integer mortgageSetupRate;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("expectedRecoverAmount")
    private Integer expectedRecoverAmount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("priorityMaximumPledgeAmount")
    private Integer priorityMaximumPledgeAmount;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    @JsonProperty("maxBondAmountBaseLtv")
    private Double maxBondAmountBaseLtv;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    @JsonProperty("shareRatio")
    private Double shareRatio;

    @JsonProperty("loanType")
    private String loanType;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("finalAmountWon")
    private Integer finalAmountWon;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("finalLength")
    private Integer finalLength;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    @JsonProperty("workPeriodYears")
    private Double workPeriodYears;

    @JsonProperty("judgeCompanySize")
    private String judgeCompanySize;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("userSex")
    private Integer userSex;

    @JsonProperty("method")
    private String method;

    @JsonProperty("isAffirmedToPurchase")
    private Boolean isAffirmedToPurchase;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("principalReturnAmountWon")
    private Integer principalReturnAmountWon;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("interestReturnAmountWon")
    private Integer interestReturnAmountWon;

    @JsonProperty("workStartDate")
    private Date workStartDate;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("repaymentDay")
    private Integer repaymentDay;

    @JsonProperty("isRenewalLoan")
    private Boolean isRenewalLoan;

    @JsonProperty("isRepaymentDayFollowingExecutionDay")
    private Boolean isRepaymentDayFollowingExecutionDay;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("startDatetime")
    private Date startDatetime;

    @JsonProperty("state")
    private String state;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("amount")
    private Integer amount;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("investmentCount")
    private Integer investmentCount;

    @JsonProperty("photos")
    private String photos;

    @JsonProperty("reason")
    private String reason;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    @JsonProperty("progress")
    private Integer progress;

    @JsonProperty("structure")
    private String structure;

    @JsonProperty("eightCreditGrade")
    private String eightCreditGrade;


    @Column(length = 2000)
    @JsonProperty("sellingPointsIconImage")
    private String sellingPointsIconImage;  // selling_points_icon_image

    @Column(length = 2000)
    @JsonProperty("sellingPointsTitle")
    private String sellingPointsTitle;

    @Column(length = 2000)
    @JsonProperty("sellingPointsDescription")
    private String sellingPointsDescription;

    @Column(length = 2000)
    @JsonProperty("sellingPointsIconImage2")
    private String sellingPointsIconImage2;

    @Column(length = 2000)
    @JsonProperty("sellingPointsTitle2")
    private String sellingPointsTitle2;

    @Column(length = 2000)
    @JsonProperty("sellingPointsDescription2")
    private String sellingPointsDescription2;

    @JsonProperty("expertName")
    private String expertName;

    @JsonProperty("expertRole")
    private String expertRole;

    @JsonProperty("expertContent")
    private String expertContent;

    @JsonProperty("hasOverdueLast1Year")
    private Boolean hasOverdueLast1Year;

    @JsonProperty("complianceNumber")
    private String complianceNumber;

    @JsonProperty("dealType")
    private String dealType;

    @JsonProperty("category")
    private String category;
}