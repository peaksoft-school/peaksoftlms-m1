package kg.peaksoft.peaksoftlmsm1.db.dto.group;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class GroupResponse {

    private Long id;
    private String group_name;
    private Date start_date;
    private String description;
    private Course course;
}
