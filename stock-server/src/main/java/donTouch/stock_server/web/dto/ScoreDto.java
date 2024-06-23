package donTouch.stock_server.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ScoreDto {
    private Integer safeScore;
    private Integer growthScore;
    private Integer dividendScore;
}
