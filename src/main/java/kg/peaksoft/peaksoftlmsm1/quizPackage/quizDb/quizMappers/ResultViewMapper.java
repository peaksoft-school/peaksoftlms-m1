package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.ResultResponse;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultViewMapper {

    public ResultResponse mapToResponse(Result result) {
        if (result == null) {
            return null;
        }
        return ResultResponse.builder()
                .id(result.getId())
                .user(result.getUser())
                .correctAnswers(result.getCorrectAnswers())
                .incorrectAnswers(result.getIncorrectAnswers())
                .percentToResult(result.getPercentOfResult())
                .build();
    }

    public List<ResultResponse> map(List<Result> results) {
        List<ResultResponse> responses = new ArrayList<>();
        for(Result result: results){
            responses.add(mapToResponse(result));
        }
        return responses;
    }
}
