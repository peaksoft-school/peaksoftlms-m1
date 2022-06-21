package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OptionRequest {

    private String option;
    private boolean isCorrect;

}
