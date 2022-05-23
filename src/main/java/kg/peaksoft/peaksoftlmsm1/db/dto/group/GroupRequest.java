package kg.peaksoft.peaksoftlmsm1.db.dto.group;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupRequest {

    private String groupName;
    private Date startDate;
    private String description;
    private Course course;
}
