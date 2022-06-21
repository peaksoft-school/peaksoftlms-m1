package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Option;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Test;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuestionResponse {

    private Long id;
    private String questionTitle;
    private List<Option> options;
    private Test test;
}
