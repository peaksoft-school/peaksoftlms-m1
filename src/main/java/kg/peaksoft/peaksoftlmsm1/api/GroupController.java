package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.GroupResponseAll;
import kg.peaksoft.peaksoftlmsm1.db.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Group controller", description = "ADMIN create, update, and delete")
@RequestMapping("api/groups")
public class GroupController {

    private final GroupService groupService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can registration group")
    @PostMapping
    public ResponseEntity<GroupResponse> create(@RequestBody @Valid GroupRequest request){
        return new ResponseEntity<>(groupService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update group")
    @PutMapping("{id}")
    public ResponseEntity<GroupResponse> update(@PathVariable Long id, @Valid @RequestBody GroupRequest request){
        GroupResponse groupResponse = groupService.update(id, request);
        return new ResponseEntity<>(groupResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get by id", description = "admin, instructor can get by id")
    @GetMapping("{id}")
    public ResponseEntity<GroupResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        groupService.delete(id);
        return new ResponseEntity<>("Group deleted successfully.", HttpStatus.OK);
    }

    @GetMapping
    public GroupResponseAll getAll(@RequestParam int size,
                                   @RequestParam int page){
        return groupService.getAllGroups(size, page);
    }
}
