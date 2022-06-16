package kg.peaksoft.peaksoftlmsm1.db.dto.course;

import kg.peaksoft.peaksoftlmsm1.db.entity.models.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CourseResponseForLesson {

    private Long id;
    private List<Lesson> lessons;

}
