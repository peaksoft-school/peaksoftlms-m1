package kg.peaksoft.peaksoftlmsm1.db.service.testService;

import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.RatingListResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.OptionRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.ResultRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.TestRepository;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.view.ResultViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.ResultResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Result;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test;
import kg.peaksoft.peaksoftlmsm1.db.enums.AccessTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static kg.peaksoft.peaksoftlmsm1.db.enums.EQuestionType.MULTI_TYPE;
import static kg.peaksoft.peaksoftlmsm1.db.enums.EQuestionType.SINGLE_TYPE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultViewMapper resultViewMapper;
    private final ResultRepository resultRepository;
    private final OptionRepository optionRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;

    public ResultResponse saveResult(AnswerRequest answerRequest, Long studentId) {

        Result result = new Result();

        Test test = testRepository.findById(answerRequest.getTestId()).orElseThrow(() -> {
            log.error("test with id = {} does not exists in database" + answerRequest.getTestId());
            throw new ResourceNotFoundException("Not found test with this id: ");
        });
        User student = userRepository.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Entity not found with: " + studentId));
        result.setTest(test);
        result.setUser(student);
        result.setTime(LocalDateTime.now());
        result.setAccessTest(AccessTest.TEST_ON);

        int countQuestionsOfTest = test.getQuestions().size();
        double correctAnswers = 0;
        int indexOfQuestionFromStudent = 0;


        for (Question question : test.getQuestions()) {
            correctAnswers += calculateCountCorrectAnswersOfQuestion(question, answerRequest.getQuestionAnswerRequestList()
                    .get(indexOfQuestionFromStudent).getSelectedAnswerId());
            indexOfQuestionFromStudent++;
        }
        result.setCorrectAnswers(correctAnswers);
        result.setIncorrectAnswers(countQuestionsOfTest - correctAnswers);
        result.setPercentOfResult(toStringInPercent(correctAnswers * 100 / countQuestionsOfTest));

        return resultViewMapper.mapToResponse(resultRepository.save(result));

    }

    public List<ResultResponse> getAllResultByTestId(Long testId) {
        log.info("Get entity test by id: {}", testId);
        Test test = testRepository.findById(testId).orElseThrow();
        return resultViewMapper.map(resultRepository.findAllByTest(test));
    }

    private double calculateCountCorrectAnswersOfQuestion(Question question, List<Long> selectedAnswerId) {

        double correctAnswers = 0;
        if (question.getQuestionType().equals(SINGLE_TYPE)) {
            for (Long aLong : selectedAnswerId) {
                if (optionRepository.findById(aLong).isPresent()) {
                    if (optionRepository.findById(aLong).get().isCorrect()) {
                        correctAnswers++;
                    }
                }
            }
        } else if (question.getQuestionType().equals(MULTI_TYPE)) {
            for (Long aLong : selectedAnswerId) {
                if (optionRepository.findById(aLong).isPresent()) {
                    if (optionRepository.findById(aLong).get().isCorrect()) {
                        correctAnswers += 0.5;
                    }
                }
            }
        }
        return correctAnswers;
    }

    private String toStringInPercent(double number) {
        return (int) number + "%";
    }

    public RatingListResponse getStudentsRating(int page, int size) {
        RatingListResponse ratingList = new RatingListResponse();
        Pageable pageable = PageRequest.of(page -1, size);
        ratingList.setResultRatingResponses(resultViewMapper.mapListRatingResult(
                resultRepository.findAllBy(pageable)));
        return ratingList;
    }

}
