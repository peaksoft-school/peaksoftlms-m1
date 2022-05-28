package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.LessonEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.LessonViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.*;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonEditMapper lessonEditMapper;
    private final LessonViewMapper lessonViewMapper;


    public LessonResponse create(LessonRequest request){
        Lesson lesson = lessonEditMapper.mapToEntity(request);
        lessonRepository.save(lesson);
        return lessonViewMapper.mapToResponse(lesson);
    }

    public LessonResponse update(Long id, LessonRequest request){
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if(lesson.isEmpty()){
            System.out.println(lesson + "with id not found");
        }
        lessonEditMapper.mapToUpdate(lesson.get(), request);
        return lessonViewMapper.mapToResponse(lessonRepository.save(lesson.get()));
    }

    public LessonResponse getById(Long id){
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if(lesson.isEmpty()){
            System.out.println(lesson + "with id not found");
        }
        return lessonViewMapper.mapToResponse(lessonRepository.save(lesson.get()));
    }

    public LessonResponse delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        lessonRepository.deleteById(id);
        return lessonViewMapper.mapToResponse(lesson);
    }
}
