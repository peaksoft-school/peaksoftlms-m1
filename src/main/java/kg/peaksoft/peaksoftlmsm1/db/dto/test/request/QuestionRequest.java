package kg.peaksoft.peaksoftlmsm1.db.dto.test.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.enumPackage.EQuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuestionRequest {

    private String questionTitle;
    private EQuestionType eQuestionType;

}
