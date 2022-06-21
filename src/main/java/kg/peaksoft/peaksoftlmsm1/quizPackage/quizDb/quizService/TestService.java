package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizService;

import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.TestRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers.TestEditMapper;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers.TestViewMapper;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.TestRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.TestResponse;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Question;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final TestEditMapper testEditMapper;
    private final TestViewMapper testViewMapper;

    public TestResponse create(TestRequest testRequest) {
        Test test = testEditMapper.mapToEntity(testRequest);
        testRepository.save(test);
        return testViewMapper.mapToResponse(test);
    }

    public TestResponse update(Long id, TestRequest groupRequest) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isEmpty()) {
            System.out.println(test + "with id not found");
        }
        testEditMapper.mapToUpdate(test.get(), groupRequest);
        return testViewMapper.mapToResponse(testRepository.save(test.get()));
    }

    public TestResponse getById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isEmpty()) {
            System.out.println(test + "with id not found");
        }
        return testViewMapper.mapToResponse(testRepository.save(test.get()));
    }

    public TestResponse delete(Long id) {
        Test test = testRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        testRepository.deleteById(id);
        return testViewMapper.mapToResponse(test);
    }

    public List<TestResponse> getAllTests() {
        return testViewMapper.map(testRepository.findAll());
    }

    public TestResponse addQuestionToTest(Long testId, Long questionId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", testId);
            throw new ResourceNotFoundException("Entity", "id", testId);
        });
        Question question = questionRepository.findById(questionId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", questionId);
            throw new ResourceNotFoundException("Entity", "id", questionId);
        });
        test.setQuestion(question);
        return testViewMapper.mapToResponse(testRepository.save(test));
    }

}
