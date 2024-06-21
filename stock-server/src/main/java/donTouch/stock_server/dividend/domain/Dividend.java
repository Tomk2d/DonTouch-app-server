package donTouch.stock_server.dividend.domain;

import donTouch.stock_server.dividend.dto.DividendDTO;
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

    public DividendDTO convertToDividendDTO(Boolean isFixed, Long quantity) {
        return new DividendDTO(id, dividendDate, isFixed, symbol, name, dividend * quantity, paymentDate);
    }

    public Integer getStockId() {
        return null;
    }
}
