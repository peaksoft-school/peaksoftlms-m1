package kg.peaksoft.peaksoftlmsm1.controller.dto.test.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AnswerRequest {

    private Long testId;
    private List<QuestionAnswerRequest> questionAnswerRequestList;

}
