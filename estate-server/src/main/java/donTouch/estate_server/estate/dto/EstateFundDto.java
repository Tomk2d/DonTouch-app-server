package donTouch.estate_server.estate.dto;

import donTouch.estate_server.estate.domain.EstateFund;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EstateFundDto {
    private int id;
    @NotBlank(message = "title 이 빈칸 입니다.")
    private String title;
    @NotNull(message = "length 가 null 입니다.")
    private int length;
    @NotBlank(message = "titleMainImageUrl 이 빈칸 입니다.")
    private String titleMainImageUrl;
    @NotNull(message = "earningRate 가 null 입니다.")
    private Double earningRate;
    @NotNull(message = "totalAmountInvestments 가 null 입니다.")
    private int totalAmountInvestments;
    @NotNull(message = "sumOfInvestmentAndReservation 가 null 입니다.")
    private Double sumOfInvestmentAndReservation;
    @NotNull(message = "loanAmountBaseLtv 가 null 입니다.")
    private Double loanAmountBaseLtv;

}
