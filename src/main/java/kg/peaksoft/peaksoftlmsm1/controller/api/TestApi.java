package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.TestService;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.TestRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.response.TestResponse;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tests")
@PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Test API", description = "Test endpoints for instructor")
public class TestApi {

    private final TestService testService;

    @Operation(summary = "method create", description = "Instructor can create Test")
    @PostMapping
    public ResponseEntity<TestResponse> create(@RequestBody @Valid TestRequest request) {
        return new ResponseEntity<>(testService.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "Instructor can update Test")
    @PutMapping("{id}")
    public ResponseEntity<TestResponse> update(@PathVariable Long id, @Valid @RequestBody TestRequest request) {
        TestResponse testResponse = testService.update(id, request);
        return new ResponseEntity<>(testResponse, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "Instructor can get by id Test")
    @GetMapping("{id}")
    public ResponseEntity<TestResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getById(id));
    }

    @Operation(summary = "method delete", description = "Instructor can delete Test")
    @DeleteMapping("{id}")
    public ResponseEntity<TestResponse> delete(@PathVariable Long id) {
        testService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Instructor can get all Tests", description = "Instructor can get all Tests")
    @GetMapping()
    public ResponseEntity<List<TestResponse>> getAll() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @Operation(summary = "Instructor can add Question to Tests", description = "Instructor can add Question to Test")
    @PostMapping("{testId}/{questionId}")
    public ResponseEntity<TestResponse> addQuestionToTest(@PathVariable("testId") Long testId,
                                                          @PathVariable("questionId") Long questionId) {
        return new ResponseEntity<>(testService.addQuestionToTest(testId, questionId), HttpStatus.OK);
    }

}
