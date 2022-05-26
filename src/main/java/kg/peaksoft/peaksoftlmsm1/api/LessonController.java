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
@RequestMapping("api/lessons")
@Tag(name = "Lesson controller", description = "Instructor can create, update, and delete")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Only Instructor can create lesson")
    public ResponseEntity<LessonResponse> create(@RequestBody @Valid LessonRequest request){
        return new ResponseEntity<>(lessonService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method update", description = "Only Instructor can update lesson")
    public ResponseEntity<LessonResponse> update(@PathVariable Long id, @Valid @RequestBody LessonRequest request){
        LessonResponse lessonResponse = lessonService.update(id, request);
        return new ResponseEntity<>(lessonResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id lesson")
    public ResponseEntity<LessonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Only Instructor can delete lesson")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return new ResponseEntity<>("Entity deleted successfully.", HttpStatus.OK);
    }

}