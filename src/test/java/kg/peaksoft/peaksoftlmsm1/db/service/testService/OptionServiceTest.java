package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers.OptionEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers.OptionViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.OptionRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response.OptionResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.enums.EQuestionType;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Option;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Question;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.OptionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class OptionServiceTest {

    @Autowired
    @InjectMocks
    private OptionService optionService;
    @Mock
    private OptionEditMapper optionEditMapper;
    @Mock
    private OptionViewMapper optionViewMapper;
    @Mock
    private OptionRepository optionRepository;
    @Mock
    private QuestionRepository questionRepository;

    private Option option;
    private Question question;

    @BeforeEach
    public void setup() {
        option = new Option(1L,"option",true,null);
        question = new Question(1L,"question",null, EQuestionType.SINGLE_TYPE, null);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save Option")
    void save() {
        OptionRequest optionRequest = new OptionRequest("option",true);

        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Mockito.when(optionEditMapper.mapToEntity(optionRequest)).thenReturn(option);
        optionService.save(question.getId(),optionRequest);

        Mockito.verify(optionRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Option.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Option by id")
    void update() {
        option.setOption("New option");
        OptionRequest optionRequest = new OptionRequest("option",true);

        Mockito.when(optionRepository.findById(1L)).thenReturn(Optional.of(option));
        Mockito.when(optionEditMapper.mapToUpdate(option,optionRequest)).thenReturn(option);
        optionService.update(1L,optionRequest);

        Mockito.verify(optionRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Option.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find Option by id")
    void getById() {
        OptionResponse optionResponse = new OptionResponse(1L,"option");

        Mockito.when(optionRepository.findById(1L)).thenReturn(Optional.of(option));
        Mockito.when(optionViewMapper.mapToResponse(Mockito.any(Option.class))).thenReturn(optionResponse);

        OptionResponse actualOptionResponse = optionService.getById(1L);

        Assertions.assertThat(actualOptionResponse.getId()).isEqualTo(optionResponse.getId());
        Assertions.assertThat(actualOptionResponse.getOption()).isEqualTo(optionResponse.getOption());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete Option by id")
    void delete() {

        Mockito.when(optionRepository.findById(1L)).thenReturn(Optional.of(option));
        optionService.delete(option.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> optionService.delete(option.getId()));
        assertThrows(ResourceNotFoundException.class, () -> optionService.getById(option.getId()));
    }

    @AfterEach
    public void tearDown() {
        option =  null;
        question = null;
    }
}