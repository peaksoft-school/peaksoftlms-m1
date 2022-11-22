package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.LinkService;
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

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "link controller", description = "INSTRUCTOR can create, update and delete")
@RequestMapping("api/teachers/links")
public class LinkApi {

    private final LinkService linkService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Only Instructor can create link")
    @PostMapping
    public ResponseEntity<LinkResponse> create(@RequestBody @Valid LinkRequest request) {
        return new ResponseEntity<>(linkService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Only Instructor can update link")
    @PutMapping("{id}")
    public ResponseEntity<LinkResponse> update(@PathVariable Long id, @Valid @RequestBody LinkRequest request) {
        LinkResponse linkResponse = linkService.update(id, request);
        return new ResponseEntity<>(linkResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id link")
    @GetMapping("{id}")
    public ResponseEntity<LinkResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(linkService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Only Instructor can delete link")
    @DeleteMapping("{id}")
    public ResponseEntity<LinkResponse> delete(@PathVariable Long id) {
        linkService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}