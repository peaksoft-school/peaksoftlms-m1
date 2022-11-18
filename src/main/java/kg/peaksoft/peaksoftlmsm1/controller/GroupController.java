package kg.peaksoft.peaksoftlmsm1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.responseAll.GroupResponseAll;
import kg.peaksoft.peaksoftlmsm1.db.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Group controller", description = "ADMIN create, update and delete")
@RequestMapping("api/admin/groups")
public class GroupController {

    private final GroupService groupService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can registration group")
    @PostMapping
    public ResponseEntity<GroupResponse> create(@RequestBody @Valid GroupRequest request){
        log.info("inside GroupController create method");
        return new ResponseEntity<>(groupService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update group")
    @PutMapping("{id}")
    public ResponseEntity<GroupResponse> update(@PathVariable Long id, @Valid @RequestBody GroupRequest request){
        log.info("inside GroupController update method");
        GroupResponse groupResponse = groupService.update(id, request);
        return new ResponseEntity<>(groupResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get by id", description = "admin, instructor can get by id")
    @GetMapping("{id}")
    public ResponseEntity<GroupResponse> getById(@PathVariable Long id) {
        log.info("inside GroupController get By Id method");
        return ResponseEntity.ok(groupService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete")
    @DeleteMapping("{id}")
    public ResponseEntity<GroupResponse> delete(@PathVariable Long id) {
        log.info("inside GroupController delete method");
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get all", description = "admin can get all groups")
    public GroupResponseAll getAll(@RequestParam int size,
                                   @RequestParam int page){
        log.info("inside GroupController get all method");
        return groupService.getAllGroups(size, page);
    }
}
