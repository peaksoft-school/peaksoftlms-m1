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

@RestController
@RequiredArgsConstructor
@RequestMapping("api/video-lessons")
@PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Video Lesson API", description = "Video lesson endpoints")
public class VideoLessonApi {

    private final VideoLessonService service;

    @Operation(summary = "method create VideoLesson", description = "Only Instructor can create VideoLesson")
    @PostMapping
    public ResponseEntity<VideoLessonResponse> create(@RequestBody @Valid VideoLessonRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "method update", description = "Only Instructor can update VideoLesson")
    @PutMapping("{id}")
    public ResponseEntity<VideoLessonResponse> update(@PathVariable Long id, @Valid @RequestBody VideoLessonRequest request) {
        VideoLessonResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "method get by id", description = "Instructor can get by id VideoLesson")
    @GetMapping("{id}")
    public ResponseEntity<VideoLessonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "method delete", description = "Only Instructor can delete VideoLesson")
    @DeleteMapping("{id}")
    public ResponseEntity<VideoLessonResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}