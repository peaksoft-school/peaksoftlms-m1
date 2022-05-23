package kg.peaksoft.peaksoftlmsm1.db.dto.course;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseResponce {

    private Long id;
    private String image;
    private String name_course;
    private Date start_course;
    private String description;
}
