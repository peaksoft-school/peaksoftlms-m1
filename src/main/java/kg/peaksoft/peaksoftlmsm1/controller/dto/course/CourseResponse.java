package kg.peaksoft.peaksoftlmsm1.controller.dto.course;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.Group;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseResponse {

    private Long id;
    private String image;
    private String nameCourse;
    private Date startCourse;
    private String description;
    private List<User> users;
    private List<Group> groups;
    private List<Lesson> lessons;

    public CourseResponse() {
    }

}
