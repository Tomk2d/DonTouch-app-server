package donTouch.stock_server.krStock.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "kr_stock_details")
public class KrStockDetail {
    @Id
    @Column(name = "id")
    int id;
    @Column(name="kr_stock_id")
    int KrstockId;
    String symbol;

    long marketCap;
    @Column(name="altman_z_score")
    double altmanzScore;
    @Column(name="piotroski_score")
    short piotroskiScore;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    LocalDateTime updatedDate;
    @Column(name = "dividend_payment_per_year")
    short dividendPaymentPerYear;

    @Column(name = "ten_y_revenue_growth_per_share")
    double tenRevenueGrowth;
    @Column(name = "five_y_revenue_growth_per_share")
    double fiveRevenueGrowth;
    @Column(name = "three_y_revenue_growth_per_share")
    double threeRevenueGrowth;

    @Column(name = "ten_y_operating_cf_growth_per_share")
    double tenOperatingCfGrowth;
    @Column(name = "five_y_operating_cf_growth_per_share")
    double fiveOperatingCfGrowth;
    @Column(name = "three_y_operating_cf_growth_per_share")
    double threeOperatingCfGrowth;

    @Column(name = "ten_y_net_income_growth_per_share")
    double tenIncomeGrowth;
    @Column(name = "five_y_net_income_growth_per_share")
    double fiveIncomeGrowth;
    @Column(name = "three_y_net_income_growth_per_share")
    double threeIncomeGrowth;

    @Column(name = "ten_y_shareholders_equity_growth_per_share")
    double tenShareholdersEquityGrowth;
    @Column(name = "five_y_shareholders_equity_growth_per_share")
    double fivehareholdersEquityGrowth;
    @Column(name = "three_y_shareholders_equity_growth_per_share")
    double threeShareholdersEquityGrowth;

    @Column(name = "ten_y_dividend_per_share_growth_per_share")
    double tenDividendGrowth;
    @Column(name = "five_y_dividend_per_share_growth_per_share")
    double fiveDividendGrowth;
    @Column(name = "three_y_dividend_per_share_growth_per_share")
    double threeDividendGrowth;
}
