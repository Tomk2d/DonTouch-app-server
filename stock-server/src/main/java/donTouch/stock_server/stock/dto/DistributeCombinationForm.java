package donTouch.stock_server.stock.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class DistributeCombinationForm {
    private List<FindStockDetailForm> combination1;
    private List<FindStockDetailForm> combination2;
    private List<FindStockDetailForm> combination3;

    private Long investmentAmount;
}
