package donTouch.stock_server.krStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "kr_latest_close")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KrLatestClose {
    @Id
    private Integer id;

    private Integer krStockId;

    private Double close;
}
