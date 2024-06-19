package donTouch.estate_server.estate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class HoldingEstateFundDto {
    private int id;
    private Long userId;
    private int estateId;

    private String titleImageUrl;
    private String title;

    private double earningRate;
    private int investmentPeriod;
    private int inputCash;
    private Date startPeriod;
    private Date createdAt;
}
