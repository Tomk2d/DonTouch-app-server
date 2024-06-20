package donTouch.order_server.holding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class DividendP2PDto {
    private String id;

    private String title;
    private String titleImageUrl;
    private Double dividendPrice;
    private LocalDate paymentDate;
}
