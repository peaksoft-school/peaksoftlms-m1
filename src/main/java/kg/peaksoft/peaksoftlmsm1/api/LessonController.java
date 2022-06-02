package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Lesson controller", description = "Instructor can create, update, and delete")
@RequestMapping("api/lessons")
public class LessonController {

    private final LessonService lessonService;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Only Instructor can create lesson")
    @PostMapping
    public ResponseEntity<LessonResponse> create(@RequestBody @Valid LessonRequest request){
        return new ResponseEntity<>(lessonService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Only Instructor can update lesson")
    @PutMapping("{id}")
    public ResponseEntity<LessonResponse> update(@PathVariable Long id, @Valid @RequestBody LessonRequest request){
        LessonResponse lessonResponse = lessonService.update(id, request);
        return new ResponseEntity<>(lessonResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id lesson")
    @GetMapping("{id}")
    public ResponseEntity<LessonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Only Instructor can delete lesson")
    @DeleteMapping("{id}")
    public ResponseEntity<LessonResponse> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}