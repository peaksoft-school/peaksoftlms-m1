package kg.peaksoft.peaksoftlmsm1.mapper;

import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupViewMapper {

    private final GroupRepository repository;

    public GroupResponse viewGroup(Group group) {
        if (group == null) {
            return null;
        }
        GroupResponse response = new GroupResponse();
        if (group.getId() != null) {
            response.setId(group.getId());
        }
        response.setGroup_name(group.getGroupName());
        response.setDate_of_start(group.getDateOfStart());
        response.setCourses(group.getCourse());
        response.setStudent(group.getStudent());
        return response;
    }

    public List<GroupResponse> viewGroups(List<Group> groups) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(viewGroup(group));
        }
        return responses;
    }
}