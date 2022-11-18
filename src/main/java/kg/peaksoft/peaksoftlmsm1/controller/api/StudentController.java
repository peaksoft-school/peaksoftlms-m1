package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseForStudentLesson;
import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.response.RatingListResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import kg.peaksoft.peaksoftlmsm1.db.service.testService.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Student controller", description = "STUDENT can use this controller")
@RequestMapping("api/students")
public class StudentController {

    private final CourseService courseService;
    private final ResultService resultService;

    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    @Operation(summary = "method get Courses by Students", description = "Student can get own Courses")
    @GetMapping("/courses")
    public List<Course> getCoursesByUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getCourses();
    }

    @GetMapping("/courses/lessons/{courseId}")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    @Operation(summary = "method get Student Lessons by Course",
            description = "Student can get Lessons to Course")
    public ResponseEntity<CourseResponseForStudentLesson> getStudentsLessonsByCourseId(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(courseService.getStudentLessonsByCourseId(courseId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    @Operation(summary = "method get all rating Students", description = "Student can get all rating")
    @GetMapping("rating")
    public RatingListResponse getAllRatingByTests(@RequestParam int size,
                                                  @RequestParam int page){
        log.info("inside CourseController get all method");
        return resultService.getStudentsRating(size, page);
    }
}