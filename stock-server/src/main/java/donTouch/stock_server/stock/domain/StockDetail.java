package donTouch.stock_server.stock.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@MappedSuperclass
public class StockDetail {
    @Id
    private Integer id;
    private String symbol;

    private Long marketCap;
    private Double peRatioTtm;

    @Column(name = "altman_z_score")
    private Double altmanZScore;
    private Integer piotroskiScore;

    @Column(name = "ten_y_revenue_growth_per_share")
    private Double tenYRevenueGrowthPerShare;
    @Column(name = "five_y_revenue_growth_per_share")
    private Double fiveYRevenueGrowthPerShare;
    @Column(name = "three_y_revenue_growth_per_share")
    private Double threeYRevenueGrowthPerShare;

    @Column(name = "ten_y_operating_cf_growth_per_share")
    private Double tenYOperatingCfGrowthPerShare;
    @Column(name = "five_y_operating_cf_growth_per_share")
    private Double fiveYOperatingCfGrowthPerShare;
    @Column(name = "three_y_operating_cf_growth_per_share")
    private Double threeYOperatingCfGrowthPerShare;

    @Column(name = "ten_y_net_income_growth_per_share")
    private Double tenYNetIncomeGrowthPerShare;
    @Column(name = "five_y_net_income_growth_per_share")
    private Double fiveYNetIncomeGrowthPerShare;
    @Column(name = "three_y_net_income_growth_per_share")
    private Double threeYNetIncomeGrowthPerShare;

    @Column(name = "ten_y_shareholders_equity_growth_per_share")
    private Double tenYShareHoldersEquityGrowthPerShare;
    @Column(name = "five_y_shareholders_equity_growth_per_share")
    private Double fiveYShareHoldersEquityGrowthPerShare;
    @Column(name = "three_y_shareholders_equity_growth_per_share")
    private Double threeYShareHoldersEquityGrowthPerShare;

    @Column(name = "ten_y_dividend_per_share_growth_per_share")
    private Double tenYDividendPerShareGrowthPerShare;
    @Column(name = "five_y_dividend_per_share_growth_per_share")
    private Double fiveYDividendPerShareGrowthPerShare;
    @Column(name = "three_y_dividend_per_share_growth_per_share")
    private Double threeYDividendPerShareGrowthPerShare;

    private LocalDate updatedDate;
}
