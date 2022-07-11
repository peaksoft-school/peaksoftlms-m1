package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.GroupEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.GroupViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.GroupResponseAll;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class GroupServiceTest {

    @Autowired
    @InjectMocks
    private GroupService groupService;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private GroupEditMapper groupEditMapper;
    @Mock
    private GroupViewMapper groupViewMapper;
    @Mock
    private Pageable pageable;

    private Group group1;
    private Group group2;

    @BeforeEach
    public void setup() {
        group1 = new Group(1L,"image","Java",null,"nine month",null,null);
        group2 = new Group(2L,"image2","Java2",null,"nine month2",null,null);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save Group")
    void create() {
        GroupRequest groupRequest = new GroupRequest("image","Java",null,"nine month");

        Mockito.when(groupEditMapper.mapToEntity(groupRequest)).thenReturn(group1);
        groupService.create(groupRequest);

        Mockito.verify(groupRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Group.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Group by id")
    void update() {
        group1.setGroupName("New Java");
        GroupRequest groupRequest = new GroupRequest("image","New Java",null,"nine month");

        Mockito.when(groupRepository.findById(1L)).thenReturn(Optional.of(group1));
        Mockito.when(groupEditMapper.mapToUpdate(group1,groupRequest)).thenReturn(group1);

        groupService.update(1L, groupRequest);

        Mockito.verify(groupRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Group.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find Group by id")
    void getById() {
        GroupResponse groupResponse = new GroupResponse(2L,"image","New Java",
                null,"nine month",null);

        Mockito.when(groupRepository.findById(2L)).thenReturn(Optional.of(group2));
        Mockito.when(groupViewMapper.mapToResponse(Mockito.any(Group.class))).thenReturn(groupResponse);

        GroupResponse actualGroupResponse = groupService.getById(2L);

        Assertions.assertThat(actualGroupResponse.getId()).isEqualTo(groupResponse.getId());
        Assertions.assertThat(actualGroupResponse.getGroupName()).isEqualTo(groupResponse.getGroupName());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete Group by id")
    void delete() {

        Mockito.when(groupRepository.findById(1L)).thenReturn(Optional.of(group1));
        groupService.delete(group1.getId());
    }

    @Test
    @Order(5)
    @DisplayName("JUnit test for {5} should get all Groups")
    void getAllGroups() {
        GroupResponse groupResponse1 = new GroupResponse(1L,"image","New Java",
                null,"nine month",null);
        GroupResponse groupResponse2 = new GroupResponse(2L,"image2","Java2",
                null,"nine month2",null);
        List<GroupResponse> groupResponses = new ArrayList<>();
        groupResponses.add(groupResponse1);
        groupResponses.add(groupResponse2);
        GroupResponseAll groupResponseAlls = new GroupResponseAll();

        Mockito.when(groupViewMapper.map(groupRepository.findGroupBy(pageable))).thenReturn(groupResponses);
        groupResponseAlls.setGroupResponses(groupResponses);
        groupService.getAllGroups(1,1);
        assertEquals(groupResponseAlls, groupResponseAlls);
        Mockito.verify(groupRepository, Mockito.times(1)).findGroupBy(pageable);

    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> groupService.delete(group1.getId()));
        assertThrows(ResourceNotFoundException.class, () -> groupService.getById(group1.getId()));
    }

    @AfterEach
    public void tearDown() {
        group1 = group2 = null;
    }

}