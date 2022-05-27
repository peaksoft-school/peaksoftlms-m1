package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.*;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final VideoLessonRepository videoLessonRepository;
    private final TaskRepository taskRepository;
    private final LinkRepository linkRepository;
    private final PresentationRepository presentationRepository;

    public LessonResponse create(LessonRequest request){
        Lesson lesson = mapToEntity(request);
        lessonRepository.save(lesson);
        return mapToResponse(lesson);
    }

    public LessonResponse update(Long id, LessonRequest request){
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if(lesson.isEmpty()){
            System.out.println(lesson + "with id not found");
        }
        mapToUpdate(lesson.get(), request);
        return mapToResponse(lessonRepository.save(lesson.get()));
    }

    public LessonResponse getById(Long id){
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if(lesson.isEmpty()){
            System.out.println(lesson + "with id not found");
        }
        return mapToResponse(lessonRepository.save(lesson.get()));
    }

    public LessonResponse delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        lessonRepository.deleteById(id);
        return mapToResponse(lesson);
    }

    public Lesson mapToEntity(LessonRequest lessonRequest){
        Lesson lesson = new Lesson();
        lesson.setName(lessonRequest.getName());
        lesson.setVideoLesson(videoLessonRepository.findById(lessonRequest.getVideoLesson()).get());
        lesson.setPresentation(presentationRepository.findById(lessonRequest.getPresentation()).get());
        lesson.setTask(taskRepository.findById(lessonRequest.getTask()).get());
        lesson.setLink(linkRepository.findById(lessonRequest.getLink()).get());
        return lesson;
    }

    public Lesson mapToUpdate(Lesson lesson, LessonRequest lessonRequest){
        lesson.setName(lessonRequest.getName());
        lesson.setVideoLesson(videoLessonRepository.findById(lessonRequest.getVideoLesson()).get());
        lesson.setPresentation(presentationRepository.findById(lessonRequest.getPresentation()).get());
        lesson.setTask(taskRepository.findById(lessonRequest.getTask()).get());
        lesson.setLink(linkRepository.findById(lessonRequest.getLink()).get());
        return lesson;
    }

    public LessonResponse mapToResponse(Lesson lesson){
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setName(lesson.getName());
        lessonResponse.setVideoLesson(lesson.getVideoLesson());
        lessonResponse.setPresentation(lesson.getPresentation());
        lessonResponse.setLink(lesson.getLink());
        lessonResponse.setTask(lesson.getTask());
        return lessonResponse;
    }

    public List<LessonResponse> map(List<Lesson> lessons){
        List<LessonResponse> responses = new ArrayList<>();
        for(Lesson lesson: lessons){
            responses.add(mapToResponse(lesson));
        }
        return responses;
    }
}
