package donTouch.stock_server.stock.domain;

import donTouch.stock_server.stock.dto.StockPriceDTO;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@MappedSuperclass
public class StockPrice {
    @Id
    Integer id;
    String symbol;

    LocalDate priceDate;
    Double closePrice;

    public StockPriceDTO convertToDTO() {
        return new StockPriceDTO(priceDate, closePrice);
    }
}
