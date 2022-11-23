package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.lesson.LessonResponse;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.edit.LessonEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.view.LessonViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonEditMapper lessonEditMapper;
    private final LessonViewMapper lessonViewMapper;

    public LessonResponse create(LessonRequest request) {
        Lesson lesson = lessonEditMapper.mapToEntity(request);
        lessonRepository.save(lesson);
        log.info("Entity lesson save: {}", lesson.getName());
        return lessonViewMapper.mapToResponse(lesson);
    }

    public LessonResponse update(Long id, LessonRequest request) {
        Optional<Lesson> lesson = Optional.ofNullable(lessonRepository.findById(id).orElseThrow(() -> {
            log.error("Entity lesson with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        lessonEditMapper.mapToUpdate(lesson.orElseThrow(NoSuchElementException::new), request);
        log.info("Entity lesson updated: {}", id);
        return lessonViewMapper.mapToResponse(lessonRepository.save(lesson.get()));
    }

    public LessonResponse getById(Long id) {
        log.info("Get entity lesson by id: {}", id);
        return lessonViewMapper.mapToResponse(lessonRepository.findById(id).orElseThrow(() -> {
            log.error("Entity lesson with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public LessonResponse delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> {
            log.error("Entity lesson with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        lessonRepository.deleteById(id);
        log.info("Delete entity lesson by id: {}", id);
        return lessonViewMapper.mapToResponse(lesson);
    }

}
