package kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Question;
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
