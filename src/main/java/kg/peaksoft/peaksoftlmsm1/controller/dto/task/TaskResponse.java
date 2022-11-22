package kg.peaksoft.peaksoftlmsm1.controller.dto.task;

import kg.peaksoft.peaksoftlmsm1.db.entity.FilePath;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;
    private String name;
    private String text;
    private String link;
    private FilePath file;
    private String image;
    private String code;

}
