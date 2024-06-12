package donTouch.stock_server.dividend.dto;

import java.time.LocalDate;

public class DividendDTO {
    private LocalDate dividendDate;
    private Boolean isFixed;
    
    private String symbol;
    private Double dividend;

    private LocalDate paymentDate;
}
