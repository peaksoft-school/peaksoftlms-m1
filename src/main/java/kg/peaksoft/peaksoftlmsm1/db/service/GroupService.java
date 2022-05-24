package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.GroupResponseAll;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public GroupResponse delete(Long id){
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        groupRepository.deleteById(id);
        return mapToResponse(group);
    }

    public GroupResponseAll getAllGroups(int page, int size){
        GroupResponseAll groupResponseAll = new GroupResponseAll();
        Pageable pageable = PageRequest.of(page-1, size);
        groupResponseAll.setGroupResponses(map(groupRepository.findGroupBy(pageable)));
        return groupResponseAll;
    }

    public Group mapToEntity(GroupRequest groupRequest){
        Group group = new Group();
        group.setImage(groupRequest.getImage());
        group.setGroupName(groupRequest.getGroupName());
        group.setStartDate(groupRequest.getStartDate());
        group.setDescription(groupRequest.getDescription());
        group.setCourse(groupRequest.getCourse());
        return group;
    }

    public Group mapToUpdate(Group group, GroupRequest groupRequest){
        group.setImage(groupRequest.getImage());
        group.setGroupName(groupRequest.getGroupName());
        group.setStartDate(groupRequest.getStartDate());
        group.setDescription(groupRequest.getDescription());
        group.setCourse(groupRequest.getCourse());
        return group;
    }

    public GroupResponse mapToResponse(Group group){
        return GroupResponse.builder()
                .image(group.getImage())
                .id(group.getId())
                .groupName(group.getGroupName())
                .startDate(group.getStartDate())
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
