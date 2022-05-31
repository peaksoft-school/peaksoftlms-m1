package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import kg.peaksoft.peaksoftlmsm1.db.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teachers")
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@Tag(name = "Teacher controller", description = "ADMIN create, update, and delete")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can create teacher")
    public ResponseEntity<TeacherResponce> create(@RequestBody @Valid TeacherRequest request){
        return new ResponseEntity<>(teacherService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update teacher")
    public ResponseEntity<TeacherResponce> update(@PathVariable Long id, @Valid @RequestBody TeacherRequest request){
        TeacherResponce teacherResponse = teacherService.update(id, request);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method getByID", description = "admin can getById teacher")
    public ResponseEntity<TeacherResponce> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete teacher")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return new ResponseEntity<>("Teacher deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/courses")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    public List<Course> getByUserCourses(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return user.getCourses();
    }
}
