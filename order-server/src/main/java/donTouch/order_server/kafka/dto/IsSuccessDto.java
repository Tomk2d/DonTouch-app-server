package donTouch.order_server.kafka.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
public class IsSuccessDto {
    boolean success;

    public IsSuccessDto() {
    }

    public IsSuccessDto(boolean success) {
        this.success = success;
    }
}
