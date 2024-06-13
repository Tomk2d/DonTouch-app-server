package donTouch.stock_server.usStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.stock_server.stock.domain.StockDetail;
import donTouch.stock_server.stock.dto.StockDetailDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "us_stock_details")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsStockDetail extends StockDetail {
    private Integer usStockId;

    public StockDetailDTO convertToDTO() {
        return new StockDetailDTO(getId(), usStockId, getSymbol(), getMarketCap(), getPeRatioTtm(),
                getTenYShareHoldersEquityGrowthPerShare(), getFiveYShareHoldersEquityGrowthPerShare(), getThreeYShareHoldersEquityGrowthPerShare(),
                getTenYDividendPerShareGrowthPerShare(), getFiveYDividendPerShareGrowthPerShare(), getThreeYDividendPerShareGrowthPerShare());
    }
}
