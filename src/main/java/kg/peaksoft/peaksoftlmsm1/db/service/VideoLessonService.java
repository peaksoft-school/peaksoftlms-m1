package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.VideoLesson;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.edit.VideoLessonEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.view.VideoLessonViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.repository.VideoLessonRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoLessonService {

    private final VideoLessonRepository repository;
    private final VideoLessonEditMapper editMapper;
    private final VideoLessonViewMapper viewMapper;

    public VideoLessonResponse create(VideoLessonRequest request) {
        VideoLesson videoLesson = editMapper.create(request);
        repository.save(videoLesson);
        log.info("Entity videoLesson save: {}", videoLesson.getName());
        return viewMapper.mapperResponse(videoLesson);
    }

    public VideoLessonResponse update(Long id, VideoLessonRequest request) {
        Optional<VideoLesson> videoLesson = Optional.ofNullable(repository.findById(id).orElseThrow(() -> {
            log.error("Entity videoLesson with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        editMapper.update(videoLesson.orElseThrow(NoSuchElementException::new), request);
        log.info("Entity videoLesson updated: {}", id);
        return viewMapper.mapperResponse(repository.save(videoLesson.get()));
    }

    public VideoLessonResponse getById(Long id) {
        log.info("Get entity videoLesson by id: {}", id);
        return viewMapper.mapperResponse(repository.findById(id).orElseThrow(() -> {
            log.error("Entity videoLesson with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public VideoLessonResponse delete(Long id) {
        VideoLesson videoLesson = repository.findById(id).orElseThrow(() -> {
            log.error("Entity videoLesson with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        repository.deleteById(id);
        log.info("Delete entity videoLesson by id: {}", id);
        return viewMapper.mapperResponse(videoLesson);
    }

}
