package kg.peaksoft.peaksoftlmsm1.db.dto.course;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseResponce {

    private Long id;
    private String image;
    private String nameCourse;
    private Date startCourse;
    private String description;
    private List<User> users;
}
