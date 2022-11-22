package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.controller.dto.responseAll.CourseResponseAll;
import kg.peaksoft.peaksoftlmsm1.db.service.CourseService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Course API", description = "Course endpoints")
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method create", description = "admin can registration course")
    @PostMapping
    public ResponseEntity<CourseResponce> create(@RequestBody @Valid CourseRequest request) {
        return new ResponseEntity<>(courseService.save(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method update", description = "admin can update course")
    @PutMapping("{id}")
    public ResponseEntity<CourseResponce> update(@PathVariable Long id, @Valid @RequestBody CourseRequest request) {
        CourseResponce courseResponse = courseService.update(id, request);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get by id", description = "admin, instructor can get by id")
    @GetMapping("{id}")
    public ResponseEntity<CourseResponce> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method delete", description = "admin can delete")
    @DeleteMapping("{id}")
    public ResponseEntity<CourseResponce> delete(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "method get all", description = "admin can get all")
    @GetMapping
    public CourseResponseAll getAll(@RequestParam int size,
                                    @RequestParam int page) {
        return courseService.getAllCourses(size, page);
    }

}
