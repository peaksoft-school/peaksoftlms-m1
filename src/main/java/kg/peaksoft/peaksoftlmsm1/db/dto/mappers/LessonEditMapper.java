package kg.peaksoft.peaksoftlmsm1.db.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.*;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizRepository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonEditMapper {

    private final VideoLessonRepository videoLessonRepository;
    private final PresentationRepository presentationRepository;
    private final TaskRepository taskRepository;
    private final LinkRepository linkRepository;
    private final TestRepository testRepository;
    private final CourseRepository courseRepository;

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
        lesson.setTest(testRepository.findById(lessonRequest.getTest()).get());
        lesson.setCourse(courseRepository.findById(lessonRequest.getCourse()).get());
        return lesson;
    }

    public Lesson mapToUpdate(Lesson lesson, LessonRequest lessonRequest){
        lesson.setName(lessonRequest.getName());
        lesson.setVideoLesson(videoLessonRepository.findById(lessonRequest.getVideoLesson()).get());
        lesson.setPresentation(presentationRepository.findById(lessonRequest.getPresentation()).get());
        lesson.setTask(taskRepository.findById(lessonRequest.getTask()).get());
        lesson.setLink(linkRepository.findById(lessonRequest.getLink()).get());
        lesson.setTest(testRepository.findById(lessonRequest.getTest()).get());
        return lesson;
    }

}
