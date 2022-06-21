package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TestResponse {

     private Long id;
     private String name;
     private List<Question> questionList;

}
