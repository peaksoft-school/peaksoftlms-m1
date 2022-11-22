package kg.peaksoft.peaksoftlmsm1.controller.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {

    private String image;
    private String groupName;
    private Date startDate;
    private String description;

}
