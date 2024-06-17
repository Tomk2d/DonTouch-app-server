package donTouch.stock_server.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockDetailDTO {
    private Integer stockId;
    private String symbol;

    private Long marketCap;
    private Double peRatioTtm;

    private Double tenYShareHoldersEquityGrowthPerShare;
    private Double fiveYShareHoldersEquityGrowthPerShare;
    private Double threeYShareHoldersEquityGrowthPerShare;

    private Double tenYDividendPerShareGrowthPerShare;
    private Double fiveYDividendPerShareGrowthPerShare;
    private Double threeYDividendPerShareGrowthPerShare;
}
