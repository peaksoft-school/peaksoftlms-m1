package kg.peaksoft.peaksoftlmsm1.controller.mappers.view;

import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.TestResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestViewMapper {

    public TestResponse mapToResponse(Test test) {
        if (test == null) {
            return null;
        }
        TestResponse response = new TestResponse();
        if (test.getId() != null) {
            response.setId(test.getId());
        }
        response.setName(test.getName());
        response.setQuestionList(test.getQuestions());
        return response;
    }

    public List<TestResponse> map(List<Test> testList) {
        List<TestResponse> responses = new ArrayList<>();
        for(Test test: testList){
            responses.add(mapToResponse(test));
        }
        return responses;
    }
}
