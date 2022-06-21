package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Question;
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
