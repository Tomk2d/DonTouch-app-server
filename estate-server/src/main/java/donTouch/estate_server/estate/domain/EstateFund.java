package donTouch.estate_server.estate.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.estate_server.estate.utils.StringToDoubleDeserializer;
import donTouch.estate_server.estate.utils.StringToIntDeserializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class EstateFund {
    @Id
    private int id;

    private String title;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int length;

    private String titleMainImageUrl;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private float earningRate;

    @JsonDeserialize(using = StringToIntDeserializer.class)
    private int totalAmountInvestments;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private float sumOfInvestmentAndReservation;

    @JsonDeserialize(using = StringToDoubleDeserializer.class)
    private float loanAmountBaseLtv;
}
