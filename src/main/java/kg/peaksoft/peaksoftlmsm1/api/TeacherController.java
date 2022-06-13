package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import kg.peaksoft.peaksoftlmsm1.db.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@Tag(name = "Teacher controller", description = "ADMIN create, update, and delete")
@RequestMapping("api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can create teacher")
    @PostMapping
    public ResponseEntity<TeacherResponse> create(@RequestBody @Valid TeacherRequest request){
        log.info("inside TeacherController create method");
        return new ResponseEntity<>(teacherService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update teacher")
    @PutMapping("{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @Valid @RequestBody TeacherRequest request){
        log.info("inside TeacherController update method");
        TeacherResponse teacherResponse = teacherService.update(id, request);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method getByID", description = "admin can getById teacher")
    @GetMapping("{id}")
    public ResponseEntity<TeacherResponse> getById(@PathVariable Long id) {
        log.info("inside TeacherController getById method");
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete teacher")
    @DeleteMapping("{id}")
    public ResponseEntity<TeacherResponse> delete(@PathVariable Long id) {
        log.info("inside TeacherController delete method");
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get Courses by User", description = "instructor can get own Courses")
    @GetMapping("/courses")
    public List<Course> getCoursesByUser(Authentication authentication){
        log.info("inside TeacherController getCoursesByUser method");
        User user = (User) authentication.getPrincipal();
        return user.getCourses();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method add Student to Course", description = "instructor can add student to course")
    @PostMapping("/courses/{courseId}/{studentId}")
    public ResponseEntity<CourseResponce> addStudentToCourse(
            @PathVariable("studentId") Long studentId,
            @PathVariable("courseId") Long courseId){
        log.info("inside TeacherController addStudentToCourse method");
        return new ResponseEntity<>(courseService.addStudentToCourse(courseId,studentId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method add Group to Course", description = "instructor can add group to course")
    @PostMapping("/courses/groups/{courseId}/{groupId}")
    public ResponseEntity<CourseResponce> addGroupToCourse(
            @PathVariable("groupId") Long groupId,
            @PathVariable("courseId")  Long courseId){
        log.info("inside TeacherController addGroupToCourse method");
        return new ResponseEntity<>(courseService.addGroupToCourse(courseId,groupId), HttpStatus.OK);
    }

}
