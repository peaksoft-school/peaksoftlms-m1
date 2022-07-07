package kg.peaksoft.peaksoftlmsm1.api.testApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.OptionService;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.OptionRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response.OptionResponse;
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
@Tag(name = "Option controller", description = "INSTRUCTOR can create, update and delete")
@RequestMapping("api/teachers/options")
public class OptionController {

    private final OptionService optionService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Instructor can registration Option")
    @PostMapping("/{questionId}")
    public ResponseEntity<OptionResponse> save(@PathVariable Long questionId,
                                               @RequestBody @Valid OptionRequest optionRequest) {
        log.info("inside OptionController create method");
        return new ResponseEntity<>(optionService.save(questionId, optionRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Instructor can update Option")
    @PutMapping("{id}")
    public ResponseEntity<OptionResponse> update(@PathVariable Long id,
                                                 @RequestBody @Valid OptionRequest request){
        log.info("inside OptionController update method");
        OptionResponse optionResponse = optionService.update(id, request);
        return new ResponseEntity<>(optionResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id Option")
    @GetMapping("{id}")
    public ResponseEntity<OptionResponse> getById(@PathVariable Long id) {
        log.info("inside OptionController get by id method");
        return ResponseEntity.ok(optionService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Instructor can delete Option")
    @DeleteMapping("{id}")
    public ResponseEntity<OptionResponse> delete(@PathVariable Long id) {
        log.info("inside OptionController delete method");
        optionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
