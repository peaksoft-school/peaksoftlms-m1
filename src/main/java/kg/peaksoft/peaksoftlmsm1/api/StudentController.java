package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.StudentService;
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
@Tag(name = "Student controller", description = "ADMIN create, update, and delete")
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can registration student")
    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody @Valid StudentRequest request){
        log.info("inside StudentController create method");
        return new ResponseEntity<>(studentService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update student")
    @PutMapping("{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request){
        log.info("inside StudentController update method");
        StudentResponse studentResponse = studentService.update(id, request);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get by id", description = "admin, instructor can get by id")
    @GetMapping("{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        log.info("inside StudentController get By Id method");
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete")
    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponse> delete(@PathVariable Long id) {
        log.info("inside StudentController delete method");
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}