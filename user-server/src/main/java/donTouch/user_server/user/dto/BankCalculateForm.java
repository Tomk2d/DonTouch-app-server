package donTouch.user_server.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
@ToString
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
public class BankCalculateForm {
    @NotNull(message = "userId 가 없습니다.")
    private Long userId;
    @NotNull(message = "price가 null 입니다.")
    private Long price;

    public BankCalculateForm() {
    }
    public BankCalculateForm(Long userId, Long price) {
        this.userId = userId;
        this.price = price;
    }
}
