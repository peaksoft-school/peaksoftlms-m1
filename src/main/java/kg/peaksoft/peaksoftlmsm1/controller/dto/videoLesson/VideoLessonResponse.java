package kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoLessonResponse {

    private Long id;
    private String name;
    private String description;
    private String link;

}
