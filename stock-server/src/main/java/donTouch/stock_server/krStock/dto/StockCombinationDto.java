package donTouch.stock_server.krStock.dto;

import jakarta.validation.constraints.*;

public class StockCombinationDto {

    @NotBlank(message = "토큰 인증이 필요합니다")
    private String token;
    @PositiveOrZero(message = "퍼센트는 양수여야 합니다")
    @Max(value = 100, message = "퍼센트는 100이하여야 합니다")
    private int safeScore;
    @Min(value = 0, message = "categoryId는 0-3 사이 숫자입니다")
    @Max(value = 100, message = "categoryId는 0-3 사이 숫자입니다")
    private int growScore;
    @PositiveOrZero(message = "퍼센트는 양수여야 합니다")
    @Max(value = 100, message = "퍼센트는 100이하여야 합니다")
    private int dividendScore;


        @Min(value = 0, message = "categoryId는 0-3 사이 숫자입니다")
        @Max(value = 3, message = "categoryId는 0-3 사이 숫자입니다")
        private int categoryId;

        public Product convertToEntity() {
            return new Product(name,price,description,categoryId);
        }

}
