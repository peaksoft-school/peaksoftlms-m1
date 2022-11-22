package kg.peaksoft.peaksoftlmsm1.controller.dto.test.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionAnswerRequest {

    private Long questionId;
    private List<Long> selectedAnswerId;

}
