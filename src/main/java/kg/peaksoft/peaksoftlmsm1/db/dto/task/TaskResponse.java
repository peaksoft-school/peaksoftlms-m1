package kg.peaksoft.peaksoftlmsm1.db.dto.task;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.FilePath;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TaskResponse {

    private Long id;
    private String name;
    private String text;
    private String link;
    private FilePath file;
    private String image;
    private String code;

}
