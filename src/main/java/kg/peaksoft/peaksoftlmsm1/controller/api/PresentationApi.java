package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.PresentationService;
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
@RequestMapping("api/presentations")
@PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Presentation API", description = "Presentation endpoints for instructor")
public class PresentationApi {

    private final PresentationService presentationService;

    @Operation(summary = "method create", description = "Only Instructor can create presentation")
    @PostMapping
    public ResponseEntity<PresentationResponse> create(@RequestBody @Valid PresentationRequest request) {
        return new ResponseEntity<>(presentationService.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "Only Instructor can update presentation")
    @PutMapping("{id}")
    public ResponseEntity<PresentationResponse> update(@PathVariable Long id, @Valid @RequestBody PresentationRequest request) {
        PresentationResponse presentationResponse = presentationService.update(id, request);
        return new ResponseEntity<>(presentationResponse, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "Instructor can get by id presentation")
    @GetMapping("{id}")
    public ResponseEntity<PresentationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(presentationService.getById(id));
    }

    @Operation(summary = "method delete", description = "Instructor can delete presentation")
    @DeleteMapping("{id}")
    public ResponseEntity<PresentationResponse> delete(@PathVariable Long id) {
        presentationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

