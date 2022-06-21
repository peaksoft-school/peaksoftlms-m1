package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizService;

import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.OptionRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.TestRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers.QuestionEditMapper;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers.QuestionViewMapper;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Question;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Test;
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
    private final OptionRepository optionRepository;

    public QuestionResponse save(Long testId, QuestionRequest questionRequest) {
        Test test = testRepository.findById(testId).orElseThrow();
        Question question = questionEditMapper.mapToEntity(questionRequest);
        question.setTest(test);
        questionRepository.save(question);
        return questionViewMapper.mapToResponse(question);
    }

    public QuestionResponse update(Long id, QuestionRequest questionRequest) {
        Optional<Question> optional = Optional.ofNullable(questionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id)));
        questionEditMapper.mapToUpdate(optional.get(), questionRequest);
        return questionViewMapper.mapToResponse(questionRepository.save(optional.get()));
    }

    public QuestionResponse getById(Long id) {
        return questionViewMapper.mapToResponse(questionRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Entity", "id", id)));
    }

    public QuestionResponse delete(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        questionRepository.deleteById(id);
        return questionViewMapper.mapToResponse(question);
    }

}
