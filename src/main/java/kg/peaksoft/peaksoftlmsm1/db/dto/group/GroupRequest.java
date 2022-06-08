package kg.peaksoft.peaksoftlmsm1.db.dto.group;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupRequest {

    private String image;
    private String groupName;
    private Date startDate;
    private String description;
}
