package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.TestService;
import kg.peaksoft.peaksoftlmsm1.api.dto.test.request.TestRequest;
import kg.peaksoft.peaksoftlmsm1.api.dto.test.request.response.TestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@Tag(name = "Test controller", description = "INSTRUCTOR create, update, get by id, get all and delete")
@RequestMapping("api/teachers/tests")
public class TestController {

    private final TestService testService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Instructor can create Test")
    @PostMapping
    public ResponseEntity<TestResponse> create(@RequestBody @Valid TestRequest request){
        log.info("inside TestController create method");
        return new ResponseEntity<>(testService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Instructor can update Test")
    @PutMapping("{id}")
    public ResponseEntity<TestResponse> update(@PathVariable Long id, @Valid @RequestBody TestRequest request){
        log.info("inside TestController update method");
        TestResponse testResponse = testService.update(id, request);
        return new ResponseEntity<>(testResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id Test")
    @GetMapping("{id}")
    public ResponseEntity<TestResponse> getById(@PathVariable Long id) {
        log.info("inside TestController get by id method");
        return ResponseEntity.ok(testService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Instructor can delete Test")
    @DeleteMapping("{id}")
    public ResponseEntity<TestResponse> delete(@PathVariable Long id) {
        log.info("inside TestController delete method");
        testService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "Instructor can get all Tests", description = "Instructor can get all Tests")
    @GetMapping()
    public ResponseEntity<List<TestResponse>> getAll(){
        log.info("inside TestController get all method");
        return ResponseEntity.ok(testService.getAllTests());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "Instructor can add Question to Tests", description = "Instructor can add Question to Test")
    @PostMapping("/{testId}/{questionId}")
    public ResponseEntity<TestResponse> addQuestionToTest(
            @PathVariable("testId") Long testId,
            @PathVariable("questionId") Long questionId) {
        log.info("inside TestController add Question to Test method");
        return new ResponseEntity<>(testService.addQuestionToTest(testId, questionId), HttpStatus.OK);
    }

}
