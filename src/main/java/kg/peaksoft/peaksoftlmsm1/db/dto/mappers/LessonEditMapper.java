package kg.peaksoft.peaksoftlmsm1.db.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.VideoLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonEditMapper {

    private final VideoLessonRepository videoLessonRepository;
    private final PresentationRepository presentationRepository;
    private final TaskRepository taskRepository;
    private final LinkRepository linkRepository;

    public Lesson mapToEntity(LessonRequest lessonRequest){
        if (lessonRequest == null) {
            return null;
        }
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

}
