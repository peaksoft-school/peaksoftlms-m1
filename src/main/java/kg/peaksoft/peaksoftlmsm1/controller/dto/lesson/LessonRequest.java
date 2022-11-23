package kg.peaksoft.peaksoftlmsm1.controller.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {

    private String name;
    private Long videoLesson;
    private Long presentation;
    private Long task;
    private Long link;
    private Long test;
    private Long course;

}