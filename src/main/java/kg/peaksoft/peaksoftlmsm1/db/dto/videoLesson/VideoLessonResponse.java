package kg.peaksoft.peaksoftlmsm1.db.dto.videoLesson;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VideoLessonResponse {

    private Long id;
    private String name;
    private String description;
    private String link;
}
