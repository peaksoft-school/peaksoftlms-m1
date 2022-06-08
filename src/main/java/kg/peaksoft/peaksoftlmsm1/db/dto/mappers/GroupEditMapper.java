package kg.peaksoft.peaksoftlmsm1.db.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupEditMapper {

    public Group mapToEntity(GroupRequest groupRequest){
        if (groupRequest == null) {
            return null;
        }
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setStartDate(groupRequest.getStartDate());
        group.setDescription(groupRequest.getDescription());
        return group;
    }

    public Group mapToUpdate(Group group, GroupRequest groupRequest){
        group.setGroupName(groupRequest.getGroupName());
        group.setStartDate(groupRequest.getStartDate());
        group.setDescription(groupRequest.getDescription());
        return group;
    }

}
