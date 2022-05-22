package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/course")
@Tag(name = "Course class", description = "User with role ADMIN, INSTRUCTOR, STUDENT can will be here")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_INSTRUCTOR','ROLE_STUDENT')")
    @Operation(summary = "all can see courses", description = "all can see courses")
    public List<Course> findAll() {
        return courseService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Admin can create", description = "Admin can create")
    public ResponseEntity<CourseResponce> create(@RequestBody @Valid CourseRequest request){
        return new ResponseEntity<>(courseService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Admin can update", description = "Admin can update")
    public ResponseEntity<CourseResponce> update(@PathVariable Long id, @Valid @RequestBody CourseRequest request){
        CourseResponce courseResponse = courseService.update(id, request);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_INSTRUCTOR','ROLE_STUDENT')")
    @Operation(summary = "Admin can update", description = "Admin can update")
    public ResponseEntity<CourseResponce> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Only Admin can delete course", description = "Only Admin can delete course")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>("Entity deleted successfully.", HttpStatus.OK);
    }
}
