package kg.peaksoft.peaksoftlmsm1.api;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/course")
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PostMapping("/save")
    public CourseResponce save(@RequestBody CourseRequest courseRequest) {
        return courseService.save(courseRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PutMapping("{id}")
    public CourseResponce update(@PathVariable Long id, @RequestBody CourseRequest request){
        return courseService.update(id, request);
    }

    @GetMapping()
    public List<Course> getAll(){
        return courseService.courseList();
    }

    @GetMapping("{id}")
    public CourseResponce getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @DeleteMapping("{id}")
    public CourseResponce delete(@PathVariable Long id) {
        return courseService.delete(id);
    }
}
