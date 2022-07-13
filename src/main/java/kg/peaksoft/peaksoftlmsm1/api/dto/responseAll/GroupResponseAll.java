package kg.peaksoft.peaksoftlmsm1.api.dto.responseAll;

import kg.peaksoft.peaksoftlmsm1.api.dto.group.GroupResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponseAll {

    private List<GroupResponse> groupResponses;
}
