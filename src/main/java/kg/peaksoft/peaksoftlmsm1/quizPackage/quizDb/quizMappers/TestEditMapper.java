package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.TestRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Test;
import org.springframework.stereotype.Component;

@Component
public class TestEditMapper {

    public Test mapToEntity(TestRequest testRequest) {
        if(testRequest == null) {
            return null;
        }
        Test test = new Test();
        test.setName(testRequest.getName());
        test.setActive(testRequest.isActive());
        return test;
    }

    public Test mapToUpdate(Test test1, TestRequest testRequest) {
        test1.setName(testRequest.getName());
        test1.setActive(testRequest.isActive());
        return test1;

    }
}
