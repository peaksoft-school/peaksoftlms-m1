package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.OptionService;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.OptionRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.response.OptionResponse;
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
@RequestMapping("api/options")
@PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Option API", description = "Option endpoints for instructor")
public class OptionApi {

    private final OptionService optionService;

    @Operation(summary = "method create", description = "Instructor can registration Option")
    @PostMapping("{questionId}")
    public ResponseEntity<OptionResponse> save(@PathVariable Long questionId, @RequestBody @Valid OptionRequest request) {
        return new ResponseEntity<>(optionService.save(questionId, request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "Instructor can update Option")
    @PutMapping("{id}")
    public ResponseEntity<OptionResponse> update(@PathVariable Long id, @RequestBody @Valid OptionRequest request) {
        OptionResponse optionResponse = optionService.update(id, request);
        return new ResponseEntity<>(optionResponse, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "Instructor can get by id Option")
    @GetMapping("{id}")
    public ResponseEntity<OptionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(optionService.getById(id));
    }

    @Operation(summary = "method delete", description = "Instructor can delete Option")
    @DeleteMapping("{id}")
    public ResponseEntity<OptionResponse> delete(@PathVariable Long id) {
        optionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
