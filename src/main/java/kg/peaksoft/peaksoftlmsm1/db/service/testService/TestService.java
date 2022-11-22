package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.TestRepository;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.testMappers.TestEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.testMappers.TestViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.TestRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.TestResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test;
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
        Optional<Test> test = Optional.ofNullable(testRepository.findById(id).orElseThrow(() -> {
            log.error("Entity test with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        testEditMapper.mapToUpdate(test.get(), groupRequest);
        return testViewMapper.mapToResponse(testRepository.save(test.get()));
    }

    public TestResponse getById(Long id) {
        log.info("Get entity test by id: {}", id);
        return testViewMapper.mapToResponse(testRepository.findById(id).orElseThrow(() -> {
            log.error("Entity test with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public TestResponse delete(Long id) {
        Test test = testRepository.findById(id).orElseThrow(() -> {
            log.error("Entity test with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        testRepository.deleteById(id);
        log.info("Delete entity test by id: {}", id);
        return testViewMapper.mapToResponse(test);
    }

    public List<TestResponse> getAllTests() {
        return testViewMapper.map(testRepository.findAll());
    }

    public TestResponse addQuestionToTest(Long testId, Long questionId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> {
            log.error("Entity test with id = {} does not exists in database", testId);
            throw new ResourceNotFoundException("Entity", "id", testId);
        });
        Question question = questionRepository.findById(questionId).orElseThrow(() -> {
            log.error("Entity question with id = {} does not exists in database", questionId);
            throw new ResourceNotFoundException("Entity", "id", questionId);
        });
        test.setQuestion(question);
        return testViewMapper.mapToResponse(testRepository.save(test));
    }

}
