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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupEditMapper groupEditMapper;
    private final GroupViewMapper groupViewMapper;


    public GroupResponse create(GroupRequest groupRequest){
      Group group = groupEditMapper.mapToEntity(groupRequest);
      groupRepository.save(group);
        log.info("Entity group save: {}", group.getGroupName());
      return groupViewMapper.mapToResponse(group);
    }

    public GroupResponse update(Long id, GroupRequest groupRequest){
        Optional<Group> group = Optional.ofNullable(groupRepository.findById(id).orElseThrow(() -> {
        log.error("Entity group with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        groupEditMapper.mapToUpdate(group.get(), groupRequest);
        log.info("Entity group updated: {}", id);
        return groupViewMapper.mapToResponse(groupRepository.save(group.get()));
    }

    public GroupResponse getById(Long id){
        log.info("Get entity group by id: {}", id);
        return groupViewMapper.mapToResponse(groupRepository.findById(id).orElseThrow(() -> {
            log.error("Entity group with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public void delete(Long id){
        groupViewMapper.mapToResponse(groupRepository.findById(id).orElseThrow(() -> {
            log.error("Entity group with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        groupRepository.deleteById(id);
        log.info("Delete entity group by id: {}", id);
    }

    public GroupResponseAll getAllGroups(int page, int size){
        GroupResponseAll groupResponseAll = new GroupResponseAll();
        Pageable pageable = PageRequest.of(page-1, size);
        groupResponseAll.setGroupResponses(groupViewMapper.map(groupRepository.findGroupBy(pageable)));
        log.info("Entity Group get all: {}");
        return groupResponseAll;
    }

}
