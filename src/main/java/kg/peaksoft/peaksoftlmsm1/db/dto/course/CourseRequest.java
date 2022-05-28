package kg.peaksoft.peaksoftlmsm1.db.dto.course;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseRequest {

    private String image;
    private String nameCourse;
    private Date startCourse;
    private String description;
    private Long user;
}
