package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.mappers.TeacherEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.TeacherViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Role;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static kg.peaksoft.peaksoftlmsm1.db.enums.Specialization.BACKEND;
import static kg.peaksoft.peaksoftlmsm1.db.enums.Specialization.FRONTEND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherServiceTest {

    private static final Long TEACHER_ID1 = 1L;

    @Mock
    private UserRepository userRepository;
    @Mock
    private TeacherEditMapper teacherEditMapper;
    @Mock
    private TeacherViewMapper teacherViewMapper;
    @InjectMocks
    private TeacherService teacherService;

    private User teacher1;
    private User teacher2;
    private TeacherResponse teacherResponse;
    private TeacherRequest teacherRequest;

    @BeforeEach
    void prepare() {
        teacher1 = User.builder()
                .id(1L)
                .firstName("teacherName")
                .lastName("teacherLastname")
                .email("teacher1@example.com")
                .phoneNumber("11222333")
                .password("qwerty111")
                .specialization(BACKEND)
                .build();

        teacher2 = User.builder()
                .id(2L)
                .firstName("teacherName2")
                .lastName("teacherLastname2")
                .email("teacher2@example.com")
                .phoneNumber("222222222")
                .password("qwerty222")
                .specialization(FRONTEND)
                .build();

        teacherResponse = TeacherResponse.builder()
                .id(1L)
                .firstName("teacherName")
                .lastName("teacherLastname")
                .email("teacher1@example.com")
                .phoneNumber("11222333")
                .password("qwerty111")
                .specialization(BACKEND)
                .build();

        var instructorRole = new Role("ROLE_INSTRUCTOR");
        teacherRequest = TeacherRequest.builder()
                .firstName("teacherName")
                .lastName("teacherLastname")
                .email("updated@email.com")
                .phoneNumber("11222333")
                .password("qwerty111")
                .specialization(BACKEND)
                .role(List.of(instructorRole))
                .build();
    }

    @Test
    @Order(1)
    void shouldNotBeNull() {
        assertNotNull(userRepository);
        assertNotNull(teacherViewMapper);
        assertNotNull(teacherEditMapper);
        assertNotNull(teacherService);
    }

    @Test
    @Order(2)
    @DisplayName("Test for save new Teacher")
    void shouldReturnTeacherIfSaved() {
        doReturn(teacher1).when(teacherEditMapper).mapToEntity(teacherRequest);
        doReturn(teacher1).when(userRepository).save(teacher1);
        doReturn(teacherResponse).when(teacherViewMapper).mapToResponse(teacher1);
        var actualTeacherResponse = teacherService.create(teacherRequest);
        assertThat(actualTeacherResponse.getId()).isEqualTo(teacherResponse.getId());
    }

    @Test
    @Order(4)
    @DisplayName("Test for get All Teachers if existed")
    void shouldReturnListOfAllTeachersExisted() {
        List<TeacherResponse> expected = new ArrayList<>();
        var teacherResponse2 = TeacherResponse.builder()
                .id(2L)
                .firstName("teacherName2")
                .lastName("teacherLastname2")
                .email("teacher2@example.com")
                .phoneNumber("2222")
                .password("qwerty222")
                .specialization(FRONTEND)
                .build();
        expected.add(teacherResponse);
        expected.add(teacherResponse2);
        List<User> userList = new ArrayList<>();
        userList.add(teacher1);
        userList.add(teacher2);
        doReturn(userList).when(userRepository).findAll();
        doReturn(expected).when(teacherViewMapper).map(userList);
        assertThat(teacherService.getAll().size()).isEqualTo(2);
    }

    @Test
    @Order(5)
    @DisplayName("Test for get empty list if Teachers not existed")
    void shouldReturnEmptyListOfAllTeachersNotExisted() {
        doReturn(Collections.emptyList()).when(userRepository).findAll();
        doReturn(Collections.emptyList()).when(teacherViewMapper).map(Collections.emptyList());
        assertThat(teacherService.getAll().size()).isEqualTo(0);
    }

    @Test
    @Order(3)
    @DisplayName("Test for get teacher by teacherId")
    void shouldReturnTeacherExistedById() {
        doReturn(Optional.of(teacher1)).when(userRepository).findById(TEACHER_ID1);
        doReturn(teacherResponse).when(teacherViewMapper).mapToResponse(teacher1);
        assertThat(teacherService.getById(TEACHER_ID1).getId()).isEqualTo(teacherResponse.getId());
    }

    @Test
    @Order(6)
    @DisplayName("Test for update teacher by teacherId with success!")
    void shouldUpdateTeacher() {
        doReturn(Optional.of(teacher1)).when(userRepository).findById(TEACHER_ID1);
        doReturn(teacher1).when(userRepository).save(teacher1);
        doReturn(teacher1).when(teacherEditMapper).mapToUpdate(teacher1, teacherRequest);
        doReturn(teacherResponse).when(teacherViewMapper).mapToResponse(teacher1);
        teacherResponse.setEmail("updated@email.com");
        var actualTeacherResponse = teacherService.update(teacher1.getId(), teacherRequest);
        assertThat(actualTeacherResponse.getEmail())
                .isEqualTo(teacherResponse.getEmail());
    }

    @Test
    @Order(7)
    @DisplayName("Test for delete teacher by teacherId with success!")
    void shouldDeleteTeacherExisted() {
        doReturn(Optional.of(teacher1)).when(userRepository).findById(TEACHER_ID1);
        doNothing().when(userRepository).deleteById(teacher1.getId());
        teacherService.delete(teacher1.getId());
        Mockito.verify(userRepository, times(1)).deleteById(TEACHER_ID1);
        assertThat(teacher1.getId()).isEqualTo(TEACHER_ID1);
    }

    @Test
    @DisplayName("Test for throw exception if teacherId not expected")
    void throwExceptionIfUserIdNotExpected() {
        var exception = assertThrows(ResourceNotFoundException.class, () -> teacherService.getById(3L));
        assertThat(exception.getMessage()).isEqualTo(String.format("%s not found with %s : '%s'", "Entity", "id", 3L));
    }

    @AfterEach
    void cleanUp() {
        teacher1 = null;
        teacher2 = null;
        teacherResponse = null;
        teacherRequest = null;
    }
}