package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.OptionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers.OptionEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers.OptionViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.OptionRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response.OptionResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Option;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionEditMapper optionEditMapper;
    private final OptionViewMapper optionViewMapper;
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    public OptionResponse save(Long questionId, OptionRequest optionRequest) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> {
            log.error("question with id = {} does not exists in database", questionId);
            throw new ResourceNotFoundException("Not found question with this id: " + questionId);
        });
        Option option = optionEditMapper.mapToEntity(optionRequest);
        option.setQuestion(question);
        return optionViewMapper.mapToResponse(optionRepository.save(option));
    }

    public OptionResponse update(Long id, OptionRequest optionRequest) {
        Option option = optionRepository.findById(id).orElseThrow(() -> {
                log.error("Entity option with id = {} does not exists in database", id);
              throw new ResourceNotFoundException("Entity", "id", id);
    });
        log.info("Entity option updated: {}", id);
        return optionViewMapper.mapToResponse(optionRepository.save(optionEditMapper.mapToUpdate(option, optionRequest)));
    }

    public OptionResponse getById(Long id) {
        log.info("Get entity option by id: {}", id);
        return optionViewMapper.mapToResponse(optionRepository.findById(id).orElseThrow(() -> {
            log.error("Entity option with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public OptionResponse delete(Long id) {
        Option option = optionRepository.findById(id).orElseThrow(() -> {
            log.error("Entity option with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        optionRepository.deleteById(id);
        log.info("Delete entity option by id: {}", id);
        return optionViewMapper.mapToResponse(option);
    }

}
