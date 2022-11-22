package kg.peaksoft.peaksoftlmsm1.controller.dto.link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkResponse {

    private Long id;
    private String text;
    private String link;

}
