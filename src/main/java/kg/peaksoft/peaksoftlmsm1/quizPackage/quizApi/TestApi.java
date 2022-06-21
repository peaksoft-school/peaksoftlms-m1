package kg.peaksoft.peaksoftlmsm1.quizPackage.quizApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizService.TestService;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.TestRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.TestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/tests")
@Tag(name = "Test controller", description = "Instructor create, update and test")
public class TestApi {

    private final TestService testService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Instructor can registration test")
    @PostMapping
    public ResponseEntity<TestResponse> create(@RequestBody @Valid TestRequest request){
        return new ResponseEntity<>(testService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Instructor can update test")
    @PutMapping("{id}")
    public ResponseEntity<TestResponse> update(@PathVariable Long id, @Valid @RequestBody TestRequest request){
        TestResponse testResponse = testService.update(id, request);
        return new ResponseEntity<>(testResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id test")
    @GetMapping("{id}")
    public ResponseEntity<TestResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Instructor can delete test")
    @DeleteMapping("{id}")
    public ResponseEntity<TestResponse> delete(@PathVariable Long id) {
        testService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "Instructor can get all Tests", description = "Instructor can get all tests")
    @GetMapping()
    public ResponseEntity<List<TestResponse>> getAll(){
        return ResponseEntity.ok(testService.getAllTests());
    }

    @PostMapping("/{testId}/{questionId}")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "", description = "")
    public ResponseEntity<TestResponse> addQuestionToTest(
            @PathVariable("testId") Long testId,
            @PathVariable("questionId") Long questionId) {
        log.info("inside TeacherController addGroupToCourse method");
        return new ResponseEntity<>(testService.addQuestionToTest(testId, questionId), HttpStatus.OK);
    }
}
