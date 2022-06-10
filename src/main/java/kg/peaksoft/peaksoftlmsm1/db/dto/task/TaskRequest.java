package kg.peaksoft.peaksoftlmsm1.db.dto.task;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TaskRequest {

    private String name;
    private String text;
    private String link;
    private Long file;
    private String image;
    private String code;

}
