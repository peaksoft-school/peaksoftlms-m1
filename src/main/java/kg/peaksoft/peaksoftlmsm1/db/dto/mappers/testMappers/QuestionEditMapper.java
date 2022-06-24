package kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Question;
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

    public void mapToUpdate(Question question, QuestionRequest questionRequest) {
        question.setQuestionTitle(questionRequest.getQuestionTitle());
        question.setQuestionType(questionRequest.getEQuestionType());
    }

}
