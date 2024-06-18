package donTouch.stock_server.stock.domain;

import donTouch.stock_server.stock.dto.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Combination {
    StockDTO stock;
    Integer price;
    Integer quantity;

    public void addQuantity() {
        quantity++;
    }

    public int getDividendPerShare() {
        return (int) (price * stock.getDividendYieldTtm());
    }

    public long getDividend() {
        return (long) (price * stock.getDividendYieldTtm()) * quantity / 4;
    }

    public long getAmount() {
        return (long) price * quantity;
    }

    public CombinationDTO convertToDTO() {
        return new CombinationDTO(stock.getId(), stock.getName(), stock.getSymbol(), price, quantity, getDividend());
    }
}
