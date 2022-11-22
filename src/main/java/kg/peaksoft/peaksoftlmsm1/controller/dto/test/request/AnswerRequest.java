package kg.peaksoft.peaksoftlmsm1.controller.dto.test.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerRequest {

    private Long testId;
    private List<QuestionAnswerRequest> questionAnswerRequestList;

}
