package donTouch.stock_server.stock.domain;

import donTouch.stock_server.stock.dto.CombinationDTO;
import donTouch.stock_server.stock.dto.StockDTO;
import donTouch.utils.exchangeRate.ExchangeRate;
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

    public int getDividendPerShareAndQuarter() {
        double dividendPerShare = price * stock.getDividendYieldTtm();

        if (stock.getExchange().equals("KSC")) {
            return (int) dividendPerShare / 4;
        }
        double exchangedDividendPerShare = ExchangeRate.USD.getSelling();
        return (int) exchangedDividendPerShare / 4;
    }

    public Long getTotalDividendPerQuarter() {
        return (long) getDividendPerShareAndQuarter() * quantity;
    }

    public long getAmount() {
        return (long) price * quantity;
    }

    public CombinationDTO convertToDTO() {
        return new CombinationDTO(stock.getId(), stock.getName(), stock.getSymbol(), stock.getExchange(), price, quantity, getTotalDividendPerQuarter());
    }
}
