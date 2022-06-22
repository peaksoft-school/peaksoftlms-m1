package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuestionAnswerRequest {

    private Long questionId;
    private List<Long> selectedAnswerId;

}