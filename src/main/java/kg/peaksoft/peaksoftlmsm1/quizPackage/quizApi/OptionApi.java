package kg.peaksoft.peaksoftlmsm1.quizPackage.quizApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizService.OptionService;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.OptionRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.OptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Answer controller", description = "Instructor can create, update and delete")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/options")
public class OptionApi {

    private final OptionService optionService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Instructor can registration answer")
    @PostMapping("/{questionId}")
    public ResponseEntity<OptionResponse> save(@PathVariable Long questionId,
                                               @RequestBody @Valid OptionRequest optionRequest) {
        return new ResponseEntity<>(optionService.save(questionId, optionRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Instructor can update answer")
    @PutMapping("{id}")
    public ResponseEntity<OptionResponse> update(@PathVariable Long id,
                                                 @RequestBody @Valid OptionRequest request){
        OptionResponse optionResponse = optionService.update(id, request);
        return new ResponseEntity<>(optionResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id answer")
    @GetMapping("{id}")
    public ResponseEntity<OptionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(optionService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Instructor can delete answer")
    @DeleteMapping("{id}")
    public ResponseEntity<OptionResponse> delete(@PathVariable Long id) {
        optionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
