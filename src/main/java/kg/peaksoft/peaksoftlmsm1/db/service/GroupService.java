package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.GroupEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.GroupViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.GroupResponseAll;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupEditMapper groupEditMapper;
    private final GroupViewMapper groupViewMapper;


    public GroupResponse create(GroupRequest groupRequest){
      Group group = groupEditMapper.mapToEntity(groupRequest);
      groupRepository.save(group);
      return groupViewMapper.mapToResponse(group);
    }

    public GroupResponse update(Long id, GroupRequest groupRequest){
        Optional<Group> group = groupRepository.findById(id);
        if(group.isEmpty()){
            System.out.println(group + "with id not found");
        }
        groupEditMapper.mapToUpdate(group.get(), groupRequest);
        return groupViewMapper.mapToResponse(groupRepository.save(group.get()));
    }

    public GroupResponse getById(Long id){
        Optional<Group> group = groupRepository.findById(id);
        if(group.isEmpty()){
            System.out.println(group + "with id not found");
        }
        return groupViewMapper.mapToResponse(groupRepository.save(group.get()));
    }

    public GroupResponse delete(Long id){
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        groupRepository.deleteById(id);
        return groupViewMapper.mapToResponse(group);
    }

    public GroupResponseAll getAllGroups(int page, int size){
        GroupResponseAll groupResponseAll = new GroupResponseAll();
        Pageable pageable = PageRequest.of(page-1, size);
        groupResponseAll.setGroupResponses(groupViewMapper.map(groupRepository.findGroupBy(pageable)));
        return groupResponseAll;
    }

}
