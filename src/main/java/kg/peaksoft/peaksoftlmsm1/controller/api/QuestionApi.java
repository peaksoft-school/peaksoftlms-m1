package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.QuestionService;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/questions")
@PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Question API", description = "Question endpoints for instructor")
public class QuestionApi {

    private final QuestionService questionService;

    @Operation(summary = "method create", description = "Instructor can registration Question")
    @PostMapping("{testId}")
    public ResponseEntity<QuestionResponse> save(@PathVariable Long testId, @RequestBody QuestionRequest request) {
        return new ResponseEntity<>(questionService.save(testId, request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "Instructor can update Question")
    @PutMapping("{id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable Long id, @RequestBody @Valid QuestionRequest request) {
        QuestionResponse questionResponse = questionService.update(id, request);
        return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "Instructor can get by id Question")
    @GetMapping("{id}")
    public ResponseEntity<QuestionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @Operation(summary = "method delete", description = "Instructor can delete Question")
    @DeleteMapping("{id}")
    public ResponseEntity<QuestionResponse> delete(@PathVariable Long id) {
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}