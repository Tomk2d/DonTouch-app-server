package donTouch.stock_server.stock.domain;

import donTouch.stock_server.stock.dto.StockDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@MappedSuperclass
public class Stock {
    @Id
    @Column(name = "id")
    int id;
    private String symbol;
    private String name;
    private String type;
    private String exchange;

    private Integer dividendMonth;
    private Double dividendYieldTtm;

    private Double safeScore;
    private Double growthScore;
    private Double dividendScore;

    private LocalDateTime updatedDate;

    public StockDTO convertToDTO(Integer userSafeScore, Integer userGrowthScore, Integer userDividendScore) {
//        Double personalizedScore = safeScore * userSafeScore + growthScore * userGrowthScore + dividendScore * userDividendScore;

        Integer maxScore = Math.max(userSafeScore, Math.max(userGrowthScore, userDividendScore));

        Double personalizedScore = safeScore * (double) userSafeScore * 4
                + growthScore * (double) userGrowthScore
                + dividendScore * (double) userDividendScore * 3;

        return new StockDTO(id, symbol, name, type, exchange, dividendMonth, dividendYieldTtm, personalizedScore);
    }

    public StockDTO convertToDTO() {
        return new StockDTO(id, symbol, name, type, exchange, dividendMonth, dividendYieldTtm, null);
    }

    public Map<String, Double> getDividedScore() {
        return null;
    }

}
