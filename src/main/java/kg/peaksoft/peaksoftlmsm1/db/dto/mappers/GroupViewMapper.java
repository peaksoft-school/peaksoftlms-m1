package kg.peaksoft.peaksoftlmsm1.db.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupViewMapper {

    public GroupResponse mapToResponse(Group group){
        if (group == null) {
            return null;
        }
        GroupResponse response = new GroupResponse();
        if (group.getId() != null) {
            response.setId(group.getId());
        }
        response.setGroupName(group.getGroupName());
        response.setDescription(group.getDescription());
        response.setStartDate(group.getStartDate());
        response.setImage(group.getImage());
        response.setCourse(group.getCourse());
        return response;
    }

    public List<GroupResponse> map(List<Group> groupList){
        List<GroupResponse> responses = new ArrayList<>();
        for(Group group: groupList){
            responses.add(mapToResponse(group));
        }
        return responses;
    }
}