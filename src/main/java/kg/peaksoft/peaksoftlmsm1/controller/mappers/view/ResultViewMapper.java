package kg.peaksoft.peaksoftlmsm1.controller.mappers.view;

import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.ResultRatingResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.response.ResultResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Result;
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
        for (Result result : results) {
            responses.add(mapToResponse(result));
        }
        return responses;
    }

    public ResultRatingResponse mapToRating(Result result) {
        if (result == null) {
            return null;
        }
        return ResultRatingResponse.builder()
                .id(result.getId())
                .user(result.getUser())
                .percentToResult(result.getPercentOfResult())
                .build();
    }

    public List<ResultRatingResponse> mapListRatingResult(List<Result> resultList) {
        List<ResultRatingResponse> resultResponseRatings = new ArrayList<>();
        for (Result result : resultList) {
            resultResponseRatings.add(mapToRating(result));
        }
        return resultResponseRatings;
    }

}
