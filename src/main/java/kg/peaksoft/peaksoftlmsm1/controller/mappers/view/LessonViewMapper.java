package kg.peaksoft.peaksoftlmsm1.controller.mappers.view;

import kg.peaksoft.peaksoftlmsm1.controller.dto.lesson.LessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonViewMapper {

    public LessonResponse mapToResponse(Lesson lesson){
        if (lesson == null) {
            return null;
        }
        LessonResponse lessonResponse = new LessonResponse();
        if (lesson.getId() != null) {
            lessonResponse.setId(lesson.getId());
        }
        lessonResponse.setName(lesson.getName());
        lessonResponse.setVideoLesson(lesson.getVideoLesson());
        lessonResponse.setPresentation(lesson.getPresentation());
        lessonResponse.setLink(lesson.getLink());
        lessonResponse.setTask(lesson.getTask());
        lessonResponse.setTest(lesson.getTest());
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
