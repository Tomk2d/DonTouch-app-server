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
@Table(name = "like_kr_stock")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LikeKrStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer krStockId;

    public LikeStockDTO convertToDTO() {
        return new LikeStockDTO(id, "KSC", krStockId);
    }
}
