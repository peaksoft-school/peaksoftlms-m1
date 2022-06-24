package kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Option;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test;
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
