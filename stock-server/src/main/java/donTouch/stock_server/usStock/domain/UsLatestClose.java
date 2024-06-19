package donTouch.stock_server.usStock.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "us_latest_close")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsLatestClose {
    @Id
    private Integer id;

    private Integer usStockId;

    private Double close;
}
