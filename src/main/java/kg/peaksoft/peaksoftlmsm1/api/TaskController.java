package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/teachers/tasks")
@Tag(name = "Task controller", description = "INSTRUCTOR can create, update and delete")
public class TaskController {

    private final TaskService service;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Only Instructor can create task")
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskRequest request) {
        log.info("inside TaskController create method");
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Only Instructor can update task")
    @PutMapping("{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        log.info("inside TaskController update method");
        TaskResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id task")
    @GetMapping("{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        log.info("inside TaskController get By Id method");
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Only Instructor can delete task")
    @DeleteMapping("{id}")
    public ResponseEntity<TaskResponse> delete(@PathVariable Long id) {
        log.info("inside TaskController delete method");
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}