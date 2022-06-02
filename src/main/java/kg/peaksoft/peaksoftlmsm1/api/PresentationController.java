package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.PresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Presentation controller", description = "Instructor can create, update, and delete")
@RequestMapping("api/presentations")
public class PresentationController {

    private final PresentationService presentationService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Only Instructor can create presentation")
    @PostMapping
    public ResponseEntity<PresentationResponse> create(@RequestBody @Valid PresentationRequest request){
        return new ResponseEntity<>(presentationService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Only Instructor can update presentation")
    @PutMapping("{id}")
    public ResponseEntity<PresentationResponse> update(@PathVariable Long id, @Valid @RequestBody PresentationRequest request){
        PresentationResponse presentationResponse = presentationService.update(id, request);
        return new ResponseEntity<>(presentationResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id presentation")
    @GetMapping("{id}")
    public ResponseEntity<PresentationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(presentationService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Instructor can delete presentation")
    @DeleteMapping("{id}")
    public ResponseEntity<PresentationResponse> delete(@PathVariable Long id) {
        presentationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

