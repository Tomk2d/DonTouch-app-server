package donTouch.stock_server.krStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.stock_server.stock.domain.StockDetail;
import donTouch.stock_server.stock.dto.StockDetailDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "kr_stock_details")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KrStockDetail extends StockDetail {
    private Integer krStockId;

    public StockDetailDTO convertToDTO() {
        return new StockDetailDTO(krStockId, getSymbol(), getMarketCap(), getPeRatioTtm(),
                getTenYShareHoldersEquityGrowthPerShare(), getFiveYShareHoldersEquityGrowthPerShare(), getThreeYShareHoldersEquityGrowthPerShare(),
                getTenYDividendPerShareGrowthPerShare(), getFiveYDividendPerShareGrowthPerShare(), getThreeYDividendPerShareGrowthPerShare());
    }
}
