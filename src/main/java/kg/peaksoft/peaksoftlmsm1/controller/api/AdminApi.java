package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.StudentService;
import kg.peaksoft.peaksoftlmsm1.db.service.TeacherService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Admin API", description = "ADMIN can create, update, get by Id, delete, get all teachers and students")
public class AdminApi {

    private final TeacherService teacherService;
    private final StudentService studentService;

    @Operation(summary = "Create teacher", description = "Admin can create Teacher")
    @PostMapping("teacher")
    public ResponseEntity<TeacherResponse> create(@RequestBody @Valid TeacherRequest request) {
        return new ResponseEntity<>(teacherService.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Update teacher", description = "Admin can update teacher")
    @PutMapping("teacher/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @Valid @RequestBody TeacherRequest request) {
        TeacherResponse teacherResponse = teacherService.update(id, request);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get teacher by id", description = "Admin can get teacher by teacher id")
    @GetMapping("teacher/{id}")
    public ResponseEntity<TeacherResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @Operation(summary = "Delete teacher", description = "Admin can delete teacher by teacher id")
    @DeleteMapping("teacher/{id}")
    public ResponseEntity<TeacherResponse> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get all teachers", description = "Admin can get all teachers")
    @GetMapping("teachers")
    public List<TeacherResponse> getAllTeachers() {
        return teacherService.getAll();
    }

    @Operation(summary = "Create student", description = "Admin can create student")
    @PostMapping("student")
    public ResponseEntity<StudentResponse> create(@RequestBody @Valid StudentRequest request) {
        return new ResponseEntity<>(studentService.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Update student", description = "Admin can update student")
    @PutMapping("student/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        StudentResponse studentResponse = studentService.update(id, request);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get student by id", description = "Admin, instructor can get by id")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @GetMapping("student/{id}")
    public ResponseEntity<StudentResponse> getByIdStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @Operation(summary = "Delete student", description = "Admin can delete student")
    @DeleteMapping("student/{id}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get all students", description = "Admin can get all students")
    @GetMapping("students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAll();
    }

    @Operation(summary = "Excel file import", description = "Admin can import excel file")
    @PostMapping("import-excel")
    public List<StudentResponse> importExcelFiles(@RequestParam(name = "file") MultipartFile files, @RequestParam Long groupId) throws IOException {
        return studentService.importStudentsExcelFile(files, groupId);
    }

}
