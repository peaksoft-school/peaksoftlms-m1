package kg.peaksoft.peaksoftlmsm1.controller.dto.group;

import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

    private Long id;
    private String image;
    private String groupName;
    private Date startDate;
    private String description;
    private List<User> users;

}
