package kg.peaksoft.peaksoftlmsm1.controller.dto.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresentationRequest {

    private String name;
    private String description;
    private String file;
    private Long lessonId;

}

