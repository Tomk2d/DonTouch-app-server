package donTouch.order_server.bankAccount.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Builder
@ToString
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
@AllArgsConstructor
public class UserBankAccountLogDto implements Comparable<UserBankAccountLogDto>{
    @NotNull
    private Long userId;
    @NotNull
    private Long inOutCash;
    @NotNull
    private int inOutType; // 0 : out, 1 : in
    @NotNull
    @Size(min = 1, max = 30)
    private String inOutTitle;
    @NotNull
    private Date inOutTime;

    @Override
    public int compareTo(UserBankAccountLogDto other) {
        return -1*this.inOutTime.compareTo(other.inOutTime);
    }
}
