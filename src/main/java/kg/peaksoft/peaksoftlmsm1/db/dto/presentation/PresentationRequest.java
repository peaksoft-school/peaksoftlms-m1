package kg.peaksoft.peaksoftlmsm1.db.dto.presentation;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PresentationRequest {

    private String name;
    private String description;
    private String file;
    private Long LessonId;
}

