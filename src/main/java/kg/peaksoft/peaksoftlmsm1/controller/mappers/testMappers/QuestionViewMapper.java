package kg.peaksoft.peaksoftlmsm1.controller.mappers.testMappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionViewMapper {

    public QuestionResponse mapToResponse(Question question) {
        if (question == null) {
            return null;
        }
        QuestionResponse response = new QuestionResponse();
        if (question.getId() != null) {
            response.setId(question.getId());
        }
        response.setQuestionTitle(question.getQuestionTitle());
        response.setOptions(question.getOptions());
        response.setTest(question.getTest());
        return response;
    }

    public List<QuestionResponse> map(List<Question> questionList) {
        List<QuestionResponse> responses = new ArrayList<>();
        for(Question question: questionList){
            responses.add(mapToResponse(question));
        }
        return responses;
    }
}
