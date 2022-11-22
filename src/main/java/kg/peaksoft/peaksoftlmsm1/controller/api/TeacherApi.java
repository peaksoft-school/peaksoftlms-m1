package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseByIdForTeacher;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseForLesson;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teachers")
@PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Teacher API", description = "Instructor endpoints")
public class TeacherApi {

    private final CourseService courseService;

    @Operation(summary = "method get Courses by User", description = "instructor can get own Courses")
    @GetMapping("courses")
    public List<Course> getCoursesByUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getCourses();
    }

    @Operation(summary = "method add Student to Course", description = "instructor can add Student to Course")
    @PostMapping("courses/{courseId}/{studentId}")
    public ResponseEntity<CourseResponse> addStudentToCourse(@PathVariable("studentId") Long studentId,
                                                             @PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(courseId, studentId), HttpStatus.OK);
    }

    @Operation(summary = "method add Group to Course", description = "instructor can add Group to Course")
    @PostMapping("courses/groups/{courseId}/{groupId}")
    public ResponseEntity<CourseResponse> addGroupToCourse(@PathVariable("groupId") Long groupId,
                                                           @PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(courseService.addGroupToCourse(courseId, groupId), HttpStatus.OK);
    }

    @Operation(summary = "method get Students by Course", description = "Instructor can get Students by Course")
    @GetMapping("courses/{courseId}")
    public ResponseEntity<CourseResponseByIdForTeacher> getAllStudentsByCourseId(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(courseService.getByCourseId(courseId), HttpStatus.OK);
    }

    @Operation(summary = "method get Lessons by Course", description = "Instructor can get Lessons to Course")
    @GetMapping("courses/lesson/{courseId}")
    public ResponseEntity<CourseResponseForLesson> getAllLessonsByCourseId(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(courseService.getLessonsByCourseId(courseId), HttpStatus.OK);
    }

}
