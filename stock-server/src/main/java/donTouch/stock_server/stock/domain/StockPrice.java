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

    LocalDate date;

    Double open;
    Double high;
    Double low;
    Double close;

    public StockPriceDTO convertToDTO() {
        return new StockPriceDTO(date, open, high, low, close);
    }
}
