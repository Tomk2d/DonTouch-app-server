package donTouch.stock_server.dividend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class DividendDTO {
    private Integer id;

    private LocalDate dividendDate;
    private Boolean isFixed;

    private String symbol;
    private Double dividend;

    private LocalDate paymentDate;
}
