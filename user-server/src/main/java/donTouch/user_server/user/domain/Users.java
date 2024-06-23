package donTouch.user_server.user.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import donTouch.user_server.user.dto.ScoreDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;
    @NonNull
    private Integer snsType;
    @Nullable
    private Date birthday;
    @NonNull
    private String nickname;
    @NonNull
    private Integer investmentType;
    @Nullable
    private Integer safeScore;
    @Nullable
    private Integer dividendScore;
    @Nullable
    private Integer growthScore;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    public void setScores(Integer safeScore, Integer growthScore, Integer dividendScore) {
        this.safeScore = safeScore;
        this.growthScore = growthScore;
        this.dividendScore = dividendScore;
    }

    public ScoreDto convertToScoreDto() {
        return new ScoreDto(safeScore, growthScore, dividendScore);
    }
}
