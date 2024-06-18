package donTouch.stock_server.stock.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DistributeCombinationForm {
    @NotNull(message = "input exchange11")
    private String exchange11;
    @NotNull(message = "input stockId11")
    private Integer stockId11;

    private String exchange12;
    private Integer stockId12;

    @NotNull(message = "input exchange21")
    private String exchange21;
    @NotNull(message = "input stockId21")
    private Integer stockId21;

    private String exchange22;
    private Integer stockId22;

    @NotNull(message = "input exchange31")
    private String exchange31;
    @NotNull(message = "input stockId31")
    private Integer stockId31;

    private String exchange32;
    private Integer stockId32;

    private Long investmentAmount;
}
