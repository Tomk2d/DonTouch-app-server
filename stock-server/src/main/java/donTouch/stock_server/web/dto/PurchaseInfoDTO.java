package donTouch.stock_server.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoDTO {
    private String symbol;
    private Long quantity;
    private Long totalPurchasePrice;

    public void increaseQuantity(Long change) {
        this.quantity += change;
    }

    public void decreaseQuantity(Long change) {
        this.quantity -= change;
    }

    public void increaseTotalPurchasePrice(Long change) {
        this.totalPurchasePrice += change;
    }

    public void decreaseTotalPurchasePrice(Long change) {
        this.totalPurchasePrice -= change;
    }
}
