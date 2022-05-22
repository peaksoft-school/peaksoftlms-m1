package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.request.UserRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.response.UserResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupResponse create(GroupRequest groupRequest){
      Group group = mapToEntity(groupRequest);
      groupRepository.save(group);
      return mapToResponse(group);
    }

    public GroupResponse update(Long id, GroupRequest groupRequest){
        Optional<Group> group = groupRepository.findById(id);
        if(group.isEmpty()){
            System.out.println(group + "with id not found");
        }
        mapToUpdate(group.get(), groupRequest);
        return mapToResponse(groupRepository.save(group.get()));
    }

    public GroupResponse getById(Long id){
        Optional<Group> group = groupRepository.findById(id);
        if(group.isEmpty()){
            System.out.println(group + "with id not found");
        }
        return mapToResponse(groupRepository.save(group.get()));
    }

    public List<Group> getAll(){
        return groupRepository.findAll();
    }

    public GroupResponse delete(Long id){
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        groupRepository.deleteById(id);
        return mapToResponse(group);
    }

    public Group mapToEntity(GroupRequest groupRequest){
        Group group = new Group();
        group.setGroupName(groupRequest.getGroup_name());
        group.setStarOfGroup(groupRequest.getStar_of_group());
        group.setDescription(groupRequest.getDescription());
        group.setCourse(groupRequest.getCourse());
        return group;
    }

    public Group mapToUpdate(Group group, GroupRequest groupRequest){
        group.setGroupName(groupRequest.getGroup_name());
        group.setStarOfGroup(groupRequest.getStar_of_group());
        group.setDescription(groupRequest.getDescription());
        group.setCourse(groupRequest.getCourse());
        return group;
    }

    public GroupResponse mapToResponse(Group group){
        return GroupResponse.builder()
                .id(group.getId())
                .group_name(group.getGroupName())
                .star_of_group(group.getStarOfGroup())
                .description(group.getDescription())
                .course(group.getCourse())
        .build();
    }

    public List<GroupResponse> map(List<Group> groupList){
        List<GroupResponse> responses = new ArrayList<>();
        for(Group group: groupList){
            responses.add(mapToResponse(group));
        }
        return responses;
    }
}
