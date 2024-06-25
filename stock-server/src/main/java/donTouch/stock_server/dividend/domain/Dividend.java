package donTouch.stock_server.dividend.domain;

import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.utils.exchangeRate.ExchangeRate;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class Dividend {
    @Id
    private Integer id;

    private LocalDate dividendDate;

    private String symbol;
    private String name;
    private Double dividend;

    private LocalDate paymentDate;

    public DividendDTO convertToDividendDTO(Boolean isKr, Boolean isFixed, Long quantity) {
        double exchangedDividend = dividend;
        if (!isKr) {
            exchangedDividend *= ExchangeRate.USD.getSelling();
        }
        long totalDividend = (long) exchangedDividend * quantity;

        return new DividendDTO(id, dividendDate, isFixed, symbol, name, quantity, totalDividend, paymentDate);
    }
}
