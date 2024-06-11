package donTouch.stock_server.krStock.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "kr_stocks")
public class KrStock {
    @Id
    @Column(name = "id")
    int id;
    @Column(unique = true)
    String symbol;
    String name;
    double safeScore;
    double growthScore;
    double dividendScore;
    short dividendMonth;
    String corpCode;
    double dividendRate;
    String type;
    String exchange;
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    LocalDateTime updatedDate;
    short dividendCount;
}
