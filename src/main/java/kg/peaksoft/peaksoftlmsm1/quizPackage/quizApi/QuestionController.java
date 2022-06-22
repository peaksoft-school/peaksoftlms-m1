package kg.peaksoft.peaksoftlmsm1.quizPackage.quizApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizService.QuestionService;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Question controller", description = "Instructor can create, update and delete")
@RequestMapping("api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Instructor can registration question")
    @PostMapping("/{testId}")
    public ResponseEntity<QuestionResponse> save(@PathVariable Long testId,
                                                 @RequestBody QuestionRequest request) {
        log.info("inside QuestionController create method");
        return new ResponseEntity<>(questionService.save(testId, request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Instructor can update question")
    @PutMapping("{id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable Long id,
                                                   @RequestBody @Valid QuestionRequest request) {
        log.info("inside QuestionController update method");
        QuestionResponse questionResponse = questionService.update(id, request);
        return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id question")
    @GetMapping("{id}")
    public ResponseEntity<QuestionResponse> getById(@PathVariable Long id) {
        log.info("inside QuestionController get by id method");
        return ResponseEntity.ok(questionService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Instructor can delete question")
    @DeleteMapping("{id}")
    public ResponseEntity<QuestionResponse> delete(@PathVariable Long id) {
        log.info("inside QuestionController delete method");
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}