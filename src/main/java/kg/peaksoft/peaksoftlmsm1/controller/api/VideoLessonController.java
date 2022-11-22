package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.service.VideoLessonService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "VideoLesson controller", description = "INSTRUCTOR can create, update and delete")
@RequestMapping("api/teachers/videoLessons")
public class VideoLessonController {

    private final VideoLessonService service;

    @Operation(summary = "method create VideoLesson", description = "Only Instructor can create VideoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @PostMapping
    public ResponseEntity<VideoLessonResponse> create(@RequestBody @Valid VideoLessonRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "Only Instructor can update VideoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @PutMapping("{id}")
    public ResponseEntity<VideoLessonResponse> update(@PathVariable Long id, @Valid @RequestBody VideoLessonRequest request) {
        VideoLessonResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "Instructor can get by id VideoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @GetMapping("{id}")
    public ResponseEntity<VideoLessonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "method delete", description = "Only Instructor can delete VideoLesson")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<VideoLessonResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}