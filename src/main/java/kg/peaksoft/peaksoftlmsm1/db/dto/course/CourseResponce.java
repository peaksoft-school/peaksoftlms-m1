package kg.peaksoft.peaksoftlmsm1.db.dto.course;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseResponce {

    private Long id;
    private String image;
    private String nameCourse;
    private Date startCourse;
    private String description;
    private List<User> users;
    private List<Group> groups;
    private List<Lesson> lessons;

    public CourseResponce(long id, String image, String java, Date startCourse, String nine, User user1, Object groups, Object lessons) {
    }
}
