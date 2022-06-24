package kg.peaksoft.peaksoftlmsm1.api.testApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.ResultService;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response.ResultResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "ResultController", description = "Instructor can get Results, Student can take the test")
@RequestMapping("/api/results")
public class ResultController {

    private  final ResultService resultService;

    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    @Operation(summary = "method save Result", description = "Saving student responses")
    @PostMapping("/myTest")
    public ResultResponse saveResult(Authentication authentication, @RequestBody AnswerRequest answerRequest) {
        log.info("inside ResultController save method");
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(answerRequest, user.getId());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get Results", description = "Instructor can get results")
    @GetMapping("/{testId}")
    public List<ResultResponse> getResults(@PathVariable Long testId) {
        log.info("inside ResultController get all method");
        return resultService.getAllResultByTestId(testId);
    }

}
