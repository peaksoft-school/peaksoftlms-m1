package kg.peaksoft.peaksoftlmsm1.api;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("api/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<GroupResponse> create(@RequestBody @Valid GroupRequest request){
        return new ResponseEntity<>(groupService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<GroupResponse> update(@PathVariable Long id, @Valid @RequestBody GroupRequest request){
        GroupResponse groupResponse = groupService.update(id, request);
        return new ResponseEntity<>(groupResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<GroupResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
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
