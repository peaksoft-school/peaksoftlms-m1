package kg.peaksoft.peaksoftlmsm1.api;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.CourseResponseAll;
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
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CourseResponce> create(@RequestBody @Valid CourseRequest request){
        return new ResponseEntity<>(courseService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CourseResponce> update(@PathVariable Long id, @Valid @RequestBody CourseRequest request){
        CourseResponce courseResponse = courseService.update(id, request);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CourseResponce> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>("Entity deleted successfully.", HttpStatus.OK);
    }

    @GetMapping
    public CourseResponseAll getAll(@RequestParam int size,
                                    @RequestParam int page){
        return courseService.getAllCourses(size, page);
    }
}
