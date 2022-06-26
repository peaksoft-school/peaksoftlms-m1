package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.request.UserRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.response.UserResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.service.StudentService;
import kg.peaksoft.peaksoftlmsm1.db.service.TeacherService;
import kg.peaksoft.peaksoftlmsm1.db.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "AdminController", description = "ADMIN can create, update, get by Id, delete, get all Teachers and Students")
public class AdminController {

    private final TeacherService teacherService;
    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can create teacher")
    @PostMapping("/teachers")
    public ResponseEntity<TeacherResponse> create(@RequestBody @Valid TeacherRequest request){
        log.info("inside TeacherController create method");
        return new ResponseEntity<>(teacherService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update teacher")
    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @Valid @RequestBody TeacherRequest request){
        log.info("inside TeacherController update method");
        TeacherResponse teacherResponse = teacherService.update(id, request);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method getByID", description = "admin can getById teacher")
    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponse> getById(@PathVariable Long id) {
        log.info("inside TeacherController getById method");
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete teacher")
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponse> delete(@PathVariable Long id) {
        log.info("inside TeacherController delete method");
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get all Teachers", description = "admin can get all teacher")
    @GetMapping("/teachers/all")
    public List<TeacherResponse> getAllTeachers() {
        return teacherService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can create student")
    @PostMapping("/students")
    public ResponseEntity<StudentResponse> create(@RequestBody @Valid StudentRequest request){
        log.info("inside StudentController create method");
        return new ResponseEntity<>(studentService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update student")
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request){
        log.info("inside StudentController update method");
        StudentResponse studentResponse = studentService.update(id, request);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get by id", description = "admin, instructor can get by id")
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponse> getByIdStudent(@PathVariable Long id) {
        log.info("inside StudentController get By Id method");
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete student")
    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable Long id) {
        log.info("inside StudentController delete method");
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get all Students", description = "admin can get all students")
    @GetMapping("/students/all")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Excel file import",
            description = "Admin can import excel file")
    @PostMapping("/students/importExcel")
    public List<StudentResponse> importExcelFiles(@RequestParam(name = "file") MultipartFile files,
                                                  @RequestParam Long groupId) throws IOException {
        return studentService.importStudentsExcelFile(files, groupId);
    }

}
