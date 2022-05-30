package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.dto.videoLesson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.videoLesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.VideoLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "VideoLesson controller", description = "Instructor can create, update, and delete")
@RequestMapping("api/videoLessons")
public class VideoLessonController {

    private final VideoLessonService service;

    @Operation(summary = "method create videoLesson", description = "Only Instructor can create videoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @PostMapping
    public ResponseEntity<VideoLessonResponse> create(@RequestBody @Valid VideoLessonRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "Only Instructor can update videoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @PutMapping("{id}")
    public ResponseEntity<VideoLessonResponse> update(@PathVariable Long id, @Valid @RequestBody VideoLessonRequest request){
        VideoLessonResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "Instructor can get by id videoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @GetMapping("{id}")
    public ResponseEntity<VideoLessonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "method delete", description = "Only Instructor can delete videoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<VideoLessonResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(service.getById(id));
    }

}