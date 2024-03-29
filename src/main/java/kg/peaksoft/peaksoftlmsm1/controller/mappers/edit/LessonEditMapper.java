package kg.peaksoft.peaksoftlmsm1.controller.mappers.edit;

import kg.peaksoft.peaksoftlmsm1.controller.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.VideoLessonRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.testRepository.TestRepository;
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

    public Lesson mapToEntity(LessonRequest lessonRequest) {
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

    public Lesson mapToUpdate(Lesson lesson, LessonRequest lessonRequest) {
        lesson.setName(lessonRequest.getName());
        lesson.setVideoLesson(videoLessonRepository.findById(lessonRequest.getVideoLesson()).get());
        lesson.setPresentation(presentationRepository.findById(lessonRequest.getPresentation()).get());
        lesson.setTask(taskRepository.findById(lessonRequest.getTask()).get());
        lesson.setLink(linkRepository.findById(lessonRequest.getLink()).get());
        lesson.setTest(testRepository.findById(lessonRequest.getTest()).get());
        return lesson;
    }

}
