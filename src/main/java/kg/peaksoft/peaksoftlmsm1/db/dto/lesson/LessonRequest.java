package kg.peaksoft.peaksoftlmsm1.db.dto.lesson;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LessonRequest {

    private String name;
    private Long videoLesson;
    private Long presentation;
    private Long task;
    private Long link;
}