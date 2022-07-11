package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers.TestEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers.TestViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.TestRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response.TestResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.enums.EQuestionType;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Question;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.QuestionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.TestRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class TestServiceTest {

    @Autowired
    @InjectMocks
    private TestService testService;
    @Mock
    private TestRepository testRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TestEditMapper testEditMapper;
    @Mock
    private TestViewMapper testViewMapper;

    private kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test test1;
    private kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test test2;

    @BeforeEach
    public void setup() {
        test1 = kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test.builder()
                .id(1L)
                .name("Java1")
                .isActive(true)
                .build();

        test2 = kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test.builder()
                .id(2L)
                .name("Java2")
                .isActive(true)
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save Test")
    void create() {
        TestRequest testRequest = new TestRequest("Java1",false);

        Mockito.when(testEditMapper.mapToEntity(testRequest)).thenReturn(test2);
        testService.create(testRequest);

        Mockito.verify(testRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Test by id")
    void update() {
        test1.setName("new Java");
        TestRequest testRequest = new TestRequest("new Java",false);

        Mockito.when(testRepository.findById(1L)).thenReturn(Optional.of(test1));
        Mockito.when(testEditMapper.mapToUpdate(test1, testRequest)).thenReturn(test1);

        testService.update(1L, testRequest);

        Mockito.verify(testRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find Test by id")
    void getById() {
        TestResponse testResponse = new TestResponse(1L,"new Java",null);

        Mockito.when(testRepository.findById(1L)).thenReturn(Optional.of(test1));
        Mockito.when(testViewMapper.mapToResponse(Mockito.any(kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test.class))).thenReturn(testResponse);
        TestResponse actualTestResponse = testService.getById(1L);

        Assertions.assertThat(actualTestResponse.getId()).isEqualTo(testResponse.getId());
        Assertions.assertThat(actualTestResponse.getName()).isEqualTo(testResponse.getName());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete Test by id")
    void delete() {

        Mockito.when(testRepository.findById(1L)).thenReturn(Optional.of(test1));
        testService.delete(test1.getId());
    }

    @Test
    @Order(5)
    @DisplayName("JUnit test for {5} should get all Tests")
    void getAllTests() {
        TestResponse testResponse = new TestResponse(1L,"new Java",null);
        TestResponse testResponse2 = new TestResponse(2L,"new Java2",null);
        List<TestResponse> testResponses = new ArrayList<>();
        testResponses.add(testResponse);
        testResponses.add(testResponse2);

        Mockito.when(testService.getAllTests()).thenReturn(testResponses);
        testResponses = testService.getAllTests();

        assertEquals(testResponses, testResponses);
        Mockito.verify(testRepository, Mockito.times(2)).findAll();
    }

    @Test
    @Order(6)
    @DisplayName("JUnit test for {6} should add Question to Test by id")
    void addQuestionToTest() {
        Mockito.when(testRepository.findById(1L)).thenReturn(Optional.of(test1));

        Question question1 = new Question(1L,"question",null, EQuestionType.SINGLE_TYPE,null);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question1);
        test1.setQuestions(questionList);
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question1));

        TestResponse testResponse = new TestResponse(1L,"new Java",questionList);

        Mockito.when(testViewMapper.mapToResponse(testRepository.save(test1))).thenReturn(testResponse);

        testService.addQuestionToTest(1L,1L);
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> testService.delete(test1.getId()));
        assertThrows(ResourceNotFoundException.class, () -> testService.getById(test1.getId()));
    }

    @AfterEach
    public void tearDown() {
        test1 = test2 = null;
    }
}