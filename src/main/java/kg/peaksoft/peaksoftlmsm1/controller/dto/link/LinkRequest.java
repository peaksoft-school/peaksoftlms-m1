package kg.peaksoft.peaksoftlmsm1.controller.dto.link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkRequest {

    private String text;
    private String link;
    private Long lessonId;

}
