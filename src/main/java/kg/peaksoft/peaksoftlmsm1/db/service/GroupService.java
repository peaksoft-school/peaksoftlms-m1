package com.peaksoft.SpringSecurityMVCToken.service.group;

import com.peaksoft.SpringSecurityMVCToken.dto.group.GroupRequest;
import com.peaksoft.SpringSecurityMVCToken.dto.group.GroupResponse;
import com.peaksoft.SpringSecurityMVCToken.dto.group.GroupResponseView;
import com.peaksoft.SpringSecurityMVCToken.mappers.group.GroupEditMapper;
import com.peaksoft.SpringSecurityMVCToken.mappers.group.GroupViewMapper;
import com.peaksoft.SpringSecurityMVCToken.models.Group;
import com.peaksoft.SpringSecurityMVCToken.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;

    public GroupResponse createGroup(GroupRequest request) {
        Group group = editMapper.createGroup(request);
        repository.save(group);
        return viewMapper.viewGroup(group);
    }

    public GroupResponse updateGroup(GroupRequest request, Long id) {
        Group group = repository.getById(id);
        editMapper.updateGroup(group, request);
        return viewMapper.viewGroup(repository.save(group));
    }

    public GroupResponse getByIdGroup(Long id) {
        Group group = repository.getById(id);
        return viewMapper.viewGroup(group);
    }

    public List<GroupResponse> getAllGroups() {
        return viewMapper.viewGroups(repository.findAll());
    }

    public GroupResponse deleteByIdGroup(Long id) {
        Group group = repository.getById(id);
        repository.deleteById(id);
        return viewMapper.viewGroup(group);
    }

    public GroupResponseView searchByDate(LocalDate date, int page, int size) {
        GroupResponseView responseView = new GroupResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setGroupResponses(viewMapper.viewGroups(viewMapper.searchGroupByDate
                (LocalDate.parse(date.toString()) , pageable)));
        return responseView;
    }

    public Long countGroup() {
        return repository.count();
    }
}
