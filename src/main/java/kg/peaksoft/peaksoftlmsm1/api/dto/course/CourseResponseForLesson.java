package kg.peaksoft.peaksoftlmsm1.api.dto.course;

import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CourseResponseForLesson {

    private Long id;
    private List<Lesson> lessons;

}
