package kg.peaksoft.peaksoftlmsm1.db.dto.lesson;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Link;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Presentation;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Task;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.VideoLesson;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LessonResponse {

    private Long id;
    private String name;
    private VideoLesson videoLesson;
    private Presentation presentation;
    private Task task;
    private Link link;
    private Test test;
}