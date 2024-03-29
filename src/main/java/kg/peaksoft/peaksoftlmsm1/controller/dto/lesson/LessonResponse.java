package kg.peaksoft.peaksoftlmsm1.controller.dto.lesson;

import kg.peaksoft.peaksoftlmsm1.db.entity.Link;
import kg.peaksoft.peaksoftlmsm1.db.entity.Presentation;
import kg.peaksoft.peaksoftlmsm1.db.entity.Task;
import kg.peaksoft.peaksoftlmsm1.db.entity.VideoLesson;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {

    private Long id;
    private String name;
    private VideoLesson videoLesson;
    private Presentation presentation;
    private Task task;
    private Link link;
    private Test test;

}