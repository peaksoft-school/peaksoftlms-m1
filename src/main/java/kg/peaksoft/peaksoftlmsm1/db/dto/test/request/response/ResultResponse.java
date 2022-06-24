package kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResultResponse {

    private Long id;
    private User user;
    private Double correctAnswers;
    private Double incorrectAnswers;
    private String percentToResult;

}
