package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.StudentService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Student controller", description = "STUDENT can use this controller")
@RequestMapping("api/students")
public class StudentController {

    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
    @Operation(summary = "method get Courses by Students", description = "Student can get own Courses")
    @GetMapping("/courses")
    public List<Course> getCoursesByUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getCourses();
    }

}