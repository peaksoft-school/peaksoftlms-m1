package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.controller.mappers.edit.QuestionEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.view.QuestionViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsm1.db.enums.EQuestionType;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.TestRepository;
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
class QuestionServiceTest {

    @Autowired
    @InjectMocks
    private QuestionService questionService;
    @Mock
    private TestRepository testRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionEditMapper questionEditMapper;
    @Mock
    private QuestionViewMapper questionViewMapper;

    private Question question;
    private kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test test;

    @BeforeEach
    public void setup() {
        question = new Question(1L,"question",null, EQuestionType.SINGLE_TYPE,null);
        test = kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test.builder()
                .id(1L)
                .name("test")
                .isActive(true)
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save Question")
    void save() {
        QuestionRequest questionRequest = new QuestionRequest("question",EQuestionType.SINGLE_TYPE);

        Mockito.when(testRepository.findById(1L)).thenReturn(Optional.of(test));
        Mockito.when(questionEditMapper.mapToEntity(questionRequest)).thenReturn(question);
        questionService.save(question.getId(),questionRequest);

        Mockito.verify(questionRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Question.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Question by id")
    void update() {
        question.setQuestionTitle("new question");
        QuestionRequest questionRequest = new QuestionRequest("new question",EQuestionType.SINGLE_TYPE);

        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Mockito.when(questionEditMapper.mapToUpdate(question,questionRequest)).thenReturn(question);

        questionService.update(1L,questionRequest);

        Mockito.verify(questionRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Question.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find Question by id")
    void getById() {
        QuestionResponse questionResponse = new QuestionResponse(1L,"new question",null,null);

        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Mockito.when(questionViewMapper.mapToResponse(Mockito.any(Question.class))).thenReturn(questionResponse);

        QuestionResponse actualQuestionResponse = questionService.getById(1L);

        Assertions.assertThat(actualQuestionResponse.getId()).isEqualTo(questionResponse.getId());
        Assertions.assertThat(actualQuestionResponse.getQuestionTitle()).isEqualTo(questionResponse.getQuestionTitle());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete Question by id")
    void delete() {

        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        questionService.delete(question.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> questionService.delete(question.getId()));
        assertThrows(ResourceNotFoundException.class, () -> questionService.getById(question.getId()));
    }

    @AfterEach
    public void tearDown() {
        question = null;
        test = null;
    }

}