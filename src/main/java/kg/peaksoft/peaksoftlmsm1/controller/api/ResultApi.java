package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.ResultService;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.response.ResultResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/results")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Result API", description = "Result endpoints for instructor & student")
public class ResultApi {

    private final ResultService resultService;

    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    @Operation(summary = "method save Result", description = "Saving Student responses")
    @PostMapping("/myTest")
    public ResultResponse saveResult(Authentication authentication, @RequestBody AnswerRequest answerRequest) {
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(answerRequest, user.getId());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get Results", description = "Instructor can get Results")
    @GetMapping("/{testId}")
    public List<ResultResponse> getResults(@PathVariable Long testId) {
        return resultService.getAllResultByTestId(testId);
    }

}
