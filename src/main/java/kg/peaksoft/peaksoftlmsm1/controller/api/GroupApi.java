package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.group.GroupResponseAll;
import kg.peaksoft.peaksoftlmsm1.db.service.GroupService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/groups")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Group API", description = "Group endpoints for admin")
public class GroupApi {

    private final GroupService groupService;

    @Operation(summary = "method create", description = "admin can registration group")
    @PostMapping
    public ResponseEntity<GroupResponse> create(@RequestBody @Valid GroupRequest request) {
        return new ResponseEntity<>(groupService.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "admin can update group")
    @PutMapping("{id}")
    public ResponseEntity<GroupResponse> update(@PathVariable Long id, @Valid @RequestBody GroupRequest request) {
        GroupResponse groupResponse = groupService.update(id, request);
        return new ResponseEntity<>(groupResponse, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "admin, instructor can get by id")
    @GetMapping("{id}")
    public ResponseEntity<GroupResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getById(id));
    }

    @Operation(summary = "method delete", description = "admin can delete")
    @DeleteMapping("{id}")
    public ResponseEntity<GroupResponse> delete(@PathVariable Long id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "method get all", description = "admin can get all groups")
    @GetMapping
    public GroupResponseAll getAll(@RequestParam int size, @RequestParam int page) {
        return groupService.getAllGroups(size, page);
    }

}
