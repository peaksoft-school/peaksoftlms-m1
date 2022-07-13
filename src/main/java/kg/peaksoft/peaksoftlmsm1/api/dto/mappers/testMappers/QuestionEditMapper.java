package kg.peaksoft.peaksoftlmsm1.api.dto.mappers.testMappers;

import kg.peaksoft.peaksoftlmsm1.api.dto.test.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionEditMapper {

    public Question mapToEntity(QuestionRequest questionRequest) {
        if (questionRequest == null) {
            return null;
        }
        Question question = new Question();
        question.setQuestionTitle(questionRequest.getQuestionTitle());
        question.setQuestionType(questionRequest.getEQuestionType());
        return question;
    }

    public Question mapToUpdate(Question question, QuestionRequest questionRequest) {
        question.setQuestionTitle(questionRequest.getQuestionTitle());
        question.setQuestionType(questionRequest.getEQuestionType());
        return question;
    }

}
