package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.api.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.api.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.api.dto.responseAll.CourseResponseAll;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Course controller", description = "ADMIN create, update and delete")
@RequestMapping("api/admin/courses")
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can registration course")
    @PostMapping
    public ResponseEntity<CourseResponce> create(@RequestBody @Valid CourseRequest request){
        log.info("inside CourseController create method");
        return new ResponseEntity<>(courseService.save(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update course")
    @PutMapping("{id}")
    public ResponseEntity<CourseResponce> update(@PathVariable Long id, @Valid @RequestBody CourseRequest request){
        CourseResponce courseResponse = courseService.update(id, request);
        log.info("inside CourseController update method");
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get by id", description = "admin, instructor can get by id")
    @GetMapping("{id}")
    public ResponseEntity<CourseResponce> getById(@PathVariable Long id) {
        log.info("inside CourseController get by id method");
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete")
    @DeleteMapping("{id}")
    public ResponseEntity<CourseResponce> delete(@PathVariable Long id) {
        log.info("inside CourseController delete method");
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get all", description = "admin can get all")
    @GetMapping
    public CourseResponseAll getAll(@RequestParam int size,
                                    @RequestParam int page){
        log.info("inside CourseController get all method");
        return courseService.getAllCourses(size, page);
    }

}
