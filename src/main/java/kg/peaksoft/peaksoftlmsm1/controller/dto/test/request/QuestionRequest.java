package kg.peaksoft.peaksoftlmsm1.controller.dto.test.request;

import kg.peaksoft.peaksoftlmsm1.db.enums.EQuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    private String questionTitle;
    private EQuestionType eQuestionType;

}
