package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseByIdForTeacher;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseForLesson;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@Tag(name = "Teacher controller", description = "INSTRUCTOR can get and add")
@RequestMapping("api/teachers")
public class TeacherController {

    private final CourseService courseService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get Courses by User", description = "instructor can get own Courses")
    @GetMapping("/courses")
    public List<Course> getCoursesByUser(Authentication authentication){
        log.info("inside TeacherController getCoursesByUser method");
        User user = (User) authentication.getPrincipal();
        return user.getCourses();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method add Student to Course", description = "instructor can add Student to Course")
    @PostMapping("/courses/{courseId}/{studentId}")
    public ResponseEntity<CourseResponce> addStudentToCourse(
            @PathVariable("studentId") Long studentId,
            @PathVariable("courseId") Long courseId){
        log.info("inside TeacherController addStudentToCourse method");
        return new ResponseEntity<>(courseService.addStudentToCourse(courseId,studentId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method add Group to Course", description = "instructor can add Group to Course")
    @PostMapping("/courses/groups/{courseId}/{groupId}")
    public ResponseEntity<CourseResponce> addGroupToCourse(
            @PathVariable("groupId") Long groupId,
            @PathVariable("courseId")  Long courseId){
        log.info("inside TeacherController addGroupToCourse method");
        return new ResponseEntity<>(courseService.addGroupToCourse(courseId,groupId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get Students by Course",
            description = "Instructor can get Students by Course")
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseResponseByIdForTeacher> getAllStudentsByCourseId(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(courseService.getByCourseId(courseId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get Lessons by Course",
            description = "Instructor can get Lessons to Course")
    @GetMapping("/courses/lesson/{courseId}")
    public ResponseEntity<CourseResponseForLesson> getAllLessonsByCourseId(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(courseService.getLessonsByCourseId(courseId), HttpStatus.OK);
    }

}
