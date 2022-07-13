package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.TestRepository;
import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.testMappers.QuestionEditMapper;
import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.testMappers.QuestionViewMapper;
import kg.peaksoft.peaksoftlmsm1.api.dto.test.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.api.dto.test.request.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final QuestionEditMapper questionEditMapper;
    private final QuestionViewMapper questionViewMapper;

    public QuestionResponse save(Long testId, QuestionRequest questionRequest) {
        Test test = testRepository.findById(testId).orElseThrow(() -> {
            log.error("test with id = {} does not exists in database", testId);
            throw new ResourceNotFoundException("Not found test with this id: " + testId);
        });
        Question question = questionEditMapper.mapToEntity(questionRequest);
        question.setTest(test);
        questionRepository.save(question);
        return questionViewMapper.mapToResponse(question);
    }

    public QuestionResponse update(Long id, QuestionRequest questionRequest) {
        Optional<Question> optional = Optional.ofNullable(questionRepository.findById(id).orElseThrow(() -> {
            log.error("Entity question with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        questionEditMapper.mapToUpdate(optional.get(), questionRequest);
        return questionViewMapper.mapToResponse(questionRepository.save(optional.get()));
    }

    public QuestionResponse getById(Long id) {
        log.info("Get entity question by id: {}", id);
        return questionViewMapper.mapToResponse(questionRepository.findById(id).orElseThrow(() -> {
            log.error("Entity question with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public QuestionResponse delete(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> {
            log.error("Entity question with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        questionRepository.deleteById(id);
        log.info("Delete entity question by id: {}", id);
        return questionViewMapper.mapToResponse(question);
    }

}
