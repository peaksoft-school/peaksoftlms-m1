package kg.peaksoft.peaksoftlmsm1.quizPackage.quizApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizService.ResultService;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.ResultResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/results")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Result", description = "The Result API")
public class ResultApi {
    private  final ResultService resultService;

    @PostMapping("/myTest")
    @Operation(summary = "",
            description = "")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    public ResultResponse saveResult(Authentication authentication, @RequestBody AnswerRequest answerRequest) {
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(answerRequest, user.getId());
    }

    @GetMapping("/{testId}")
    @Operation(summary = "",
            description = "")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    public List<ResultResponse> getResults(@PathVariable Long testId) {
        return resultService.getAllResultByTestId(testId);
    }
}
