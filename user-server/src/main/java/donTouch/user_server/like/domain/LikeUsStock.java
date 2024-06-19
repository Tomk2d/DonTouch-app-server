package donTouch.user_server.like.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.user_server.like.dto.LikeStockDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "like_us_stock")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LikeUsStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String exchange;
    private Integer usStockId;

    public LikeStockDTO convertToDTO() {
        return new LikeStockDTO(id, exchange, usStockId);
    }
}
