package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.controller.mappers.ResultViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.QuestionAnswerRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.ResultResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.enums.AccessTest;
import kg.peaksoft.peaksoftlmsm1.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Option;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Result;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.OptionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.ResultRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.TestRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kg.peaksoft.peaksoftlmsm1.db.enums.EQuestionType.MULTI_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ResultServiceTest {
    @InjectMocks
    private ResultService resultService;
    @Mock
    private ResultViewMapper resultViewMapper;
    @Mock
    private ResultRepository resultRepository;
    @Mock
    private OptionRepository optionRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TestRepository testRepository;

    private ResultResponse resultResponse;
    private Question question;
    private Result result;
    private AnswerRequest answerRequest;
    private kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test testForStudent;
    private User student1;
    private Option option;
    private List<Result> resultList;
    private List<ResultResponse> resultResponseList;
    private List<QuestionAnswerRequest> questionAnswerRequestList;

    @BeforeEach
    void prepare() {
        Lesson lesson = Lesson.builder().id(1L).name("ame").test(null).build();

        testForStudent = kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test
                .builder()
                .id(1L)
                .name("test1")
                .lesson(lesson)
                .isActive(true)
                .questions(null)
                .testResults(null)
                .build();
        question = Question.builder()
                .id(1L)
                .questionTitle("title1")
                .options(null)
                .questionType(MULTI_TYPE)
                .test(null)
                .build();
        QuestionAnswerRequest questionAnswerRequest = QuestionAnswerRequest
                .builder()
                .questionId(question.getId())
                .selectedAnswerId(List.of(1L, 2L, 3L, 4L))
                .build();
        questionAnswerRequestList = new ArrayList<>();
        questionAnswerRequestList.add(questionAnswerRequest);

        answerRequest = AnswerRequest.builder()
                .testId(1L)
                .questionAnswerRequestList(List.of(questionAnswerRequest))
                .build();


        option = Option.builder()
                .id(1L)
                .option("optionName")
                .isCorrect(true)
                .question(null)
                .build();

        student1 = User.builder()
                .id(1L)
                .firstName("studentName")
                .lastName("studentLastname")
                .email("student1@example.com")
                .phoneNumber("11222333")
                .password("qwerty111")
                .groups(null)
                .studyFormat(StudyFormat.ONLINE)
                .build();

        result = Result.builder()
                .id(1L)
                .correctAnswers(5)
                .incorrectAnswers(5)
                .percentOfResult("50%")
                .accessTest(AccessTest.TEST_ON)
                .test(testForStudent)
                .user(student1)
                .build();

        resultList = List.of(result);

        resultResponse = ResultResponse.builder()
                .id(1L)
                .correctAnswers(5.0)
                .incorrectAnswers(5.0)
                .percentToResult("50%")
                .user(student1)
                .build();

        resultResponseList = List.of(resultResponse);
    }

    @Test
    @Order(1)
    void shouldNotBeNull() {
        assertNotNull(userRepository);
        assertNotNull(resultViewMapper);
        assertNotNull(resultService);
        assertNotNull(resultRepository);
        assertNotNull(optionRepository);
        assertNotNull(testRepository);
    }

    @Test
    @Order(2)
    @DisplayName("Test for save student's result of test")
    void shouldReturnResultOfTestForStudentSaved() {
        testForStudent.setQuestion(question);
        doReturn(Optional.of(student1)).when(userRepository).findById(1L);
        doReturn(Optional.of(testForStudent)).when(testRepository).findById(1L);
        assertThat(testForStudent.getQuestions().size()).isEqualTo(1);
        doReturn(Optional.of(option)).when(optionRepository).findById(1L);
        assertThat(questionAnswerRequestList.size()).isEqualTo(1);
        when(resultViewMapper.mapToResponse(resultRepository.save(result))).thenReturn(resultResponse);
        ResultResponse resultResponse1 = resultService.saveResult(answerRequest, 1L);
        assertThat(resultResponse1.getId()).isEqualTo(resultResponse.getId());
    }

    @Test
    @Order(3)
    @DisplayName("Test for get all results of test by testId")
    void shouldReturnAllStudentsResultByTestId() {
        when(testRepository.findById(1L)).thenReturn(Optional.of(testForStudent));
        when(resultRepository.findAllByTest(testForStudent)).thenReturn(resultList);
        when(resultViewMapper.map(resultList)).thenReturn(resultResponseList);
        List<ResultResponse> allResultByTestId = resultService.getAllResultByTestId(1L);
        assertThat(allResultByTestId.size()).isEqualTo(resultResponseList.size());
    }

    @AfterEach
    void cleanUp() {
        resultResponse = null;
        question = null;
        result = null;
        answerRequest = null;
        testForStudent = null;
        student1 = null;
        option = null;
        resultList = null;
        resultResponseList = null;
        questionAnswerRequestList = null;
    }
}