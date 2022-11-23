package kg.peaksoft.peaksoftlmsm1.controller.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private String name;
    private String text;
    private String link;
    private Long file;
    private String image;
    private String code;

}
