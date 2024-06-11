package donTouch.stock_server.krStock.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "kr_stock_prices")
public class KrStockPrice {
    @Id
    @Column(name = "id")
    int id;
    @Column(name="kr_stock_id")
    int KrstockId;
    String symbol;
    @Column(name = "price_date", columnDefinition = "DATE")
    LocalDate priceDate;
    double closePrice;
}
