package donTouch.stock_server.dividend.domain;

import donTouch.stock_server.dividend.dto.DividendDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class Dividend {
    private String symbol;

    private Double dividend;

    private LocalDate dividendDate;

    private LocalDate paymentDate;

    public DividendDTO convertToDividendDTO(Boolean isFixed) {
        return new DividendDTO();
    }
}
