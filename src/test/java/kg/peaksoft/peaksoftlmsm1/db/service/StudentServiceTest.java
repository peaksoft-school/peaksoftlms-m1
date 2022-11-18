package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.StudentEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.StudentViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Role;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsm1.db.entity.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private StudentEditMapper studentEditMapper;
    @Mock
    private StudentViewMapper studentViewMapper;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private XSSFSheet sheet;
    @Mock
    private Row row;
    @Mock
    MultipartFile files;
    @InjectMocks
    private StudentService studentService;


    private static final Long STUDENT_ID1 = 1L;
    private User student1;
    private User student2;
    private StudentResponse studentResponse;
    private StudentResponse studentResponse2;
    private StudentRequest studentRequest;

    @BeforeEach
    void prepare() {
        student1 = User.builder()
                .id(STUDENT_ID1)
                .firstName("studentName")
                .lastName("studentLastname")
                .email("student1@example.com")
                .phoneNumber("11222333")
                .password("qwerty111")
                .groups(null)
                .studyFormat(StudyFormat.ONLINE)
                .build();

        student2 = User.builder()
                .id(2L)
                .firstName("studentName2")
                .lastName("studentLastname2")
                .email("student2@example.com")
                .phoneNumber("222222222")
                .password("qwerty222")
                .groups(null)
                .studyFormat(StudyFormat.OFFLINE)
                .build();

        studentResponse = StudentResponse.builder()
                .id(1L)
                .firstName("studentName")
                .lastName("studentLastname")
                .email("student1@example.com")
                .phoneNumber("11222333")
                .password("qwerty111")
                .group(null)
                .studyFormat(StudyFormat.ONLINE)
                .build();
        studentResponse2 = StudentResponse.builder()
                .id(2L)
                .firstName("studentName2")
                .lastName("studentLastname2")
                .email("student2@example.com")
                .phoneNumber("222222222")
                .password("qwerty222")
                .group(null)
                .studyFormat(StudyFormat.OFFLINE)
                .build();

        var instructorRole = new Role("ROLE_STUDENT");
        studentRequest = StudentRequest.builder()
                .firstName("studentName")
                .lastName("studentLastname")
                .email("student1@example.com")
                .phoneNumber("11222333")
                .password("qwerty111")
                .group(null)
                .studyFormat(StudyFormat.ONLINE)
                .role(List.of(instructorRole))
                .build();
    }

    @Test
    @Order(1)
    void shouldNotBeNull() {
        assertNotNull(userRepository);
        assertNotNull(studentEditMapper);
        assertNotNull(studentViewMapper);
        assertNotNull(studentService);
    }

    @Test
    @Order(2)
    @DisplayName("Test for save new Student")
    void shouldReturnStudentIfSaved() {
        doReturn(student1).when(studentEditMapper).mapToEntity(studentRequest);
        doReturn(student1).when(userRepository).save(student1);
        doReturn(studentResponse).when(studentViewMapper).mapToResponse(student1);
        assertThat(studentService.create(studentRequest).getId()).isEqualTo(studentResponse.getId());
    }

    @Test
    @Order(4)
    @DisplayName("Test for update student by teacherId")
    void shouldUpdateStudent() {
        doReturn(Optional.of(student1)).when(userRepository).findById(STUDENT_ID1);
        doReturn(student1).when(userRepository).save(student1);
        doReturn(student1).when(studentEditMapper).mapToUpdate(student1, studentRequest);
        doReturn(studentResponse).when(studentViewMapper).mapToResponse(student1);
        studentResponse.setEmail("updated@email.com");
        assertThat(studentService.update(student1.getId(), studentRequest).getEmail())
                .isEqualTo(studentResponse.getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("Test for get student by teacherId")
    void shouldReturnStudentExistedById() {
        doReturn(Optional.of(student1)).when(userRepository).findById(STUDENT_ID1);
        doReturn(studentResponse).when(studentViewMapper).mapToResponse(student1);
        assertThat(studentService.getById(STUDENT_ID1).getId()).isEqualTo(studentResponse.getId());
    }

    @Test
    @Order(6)
    @DisplayName("Test for delete student by teacherId")
    void shouldDeleteStudentExisted() {
        doReturn(Optional.of(student1)).when(userRepository).findById(STUDENT_ID1);
        doNothing().when(userRepository).deleteById(student1.getId());
        studentService.delete(student1.getId());
        Mockito.verify(userRepository, times(1)).deleteById(STUDENT_ID1);
        assertThat(student1.getId()).isEqualTo(STUDENT_ID1);
    }

    @Test
    @Order(5)
    @DisplayName("Test for get All Students")
    void shouldReturnListOfAllStudentsExisted() {
        List<StudentResponse> expected = new ArrayList<>();

        expected.add(studentResponse);
        expected.add(studentResponse2);
        List<User> userList = new ArrayList<>();
        userList.add(student1);
        userList.add(student2);

        when(userRepository.findAll()).thenReturn(userList);
        when(studentViewMapper.map(userList)).thenReturn(expected);
        List<StudentResponse> all = studentService.getAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    @Order(5)
    @DisplayName("Test for get empty list if Students if not existed with success!")
    void shouldReturnEmptyListOfAllStudentsIfNotExisted() {
        doReturn(Collections.emptyList()).when(userRepository).findAll();
        doReturn(Collections.emptyList()).when(studentViewMapper).map(Collections.emptyList());
        assertThat(studentService.getAll().size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    @DisplayName("JUnit test for {5} should import Excel file")
    void importStudentsExcelFile() throws IOException {

        Group group = Group.builder().id(1L).groupName("Java").image("image").description("nine month").build();

        List<User> userList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        for (int index = 0; index < sheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                User user = new User();

                row = sheet.getRow(index);
                user.setFirstName(formatter.formatCellValue(row.getCell(0)));
                user.setLastName(formatter.formatCellValue(row.getCell(1)));
                user.setStudyFormat(StudyFormat.valueOf(formatter.formatCellValue(row.getCell(2))));
                user.setPhoneNumber((formatter.formatCellValue(row.getCell(3))));
                user.setEmail(formatter.formatCellValue(row.getCell(4)));
                user.setPassword(formatter.formatCellValue(row.getCell(5)));
                user.setCreated(LocalDateTime.now());
                userList.add(user);
            }

            doReturn(student1).when(userRepository).save(student1);
            List<StudentResponse> studentResponses = new ArrayList<>();
            doReturn(studentResponse).when(studentViewMapper).mapToResponse(student1);
            studentResponses.add(studentResponse);
            doReturn(studentResponses).when(studentViewMapper).map(userList);
            assertEquals(studentService.importStudentsExcelFile(files, group.getId()), studentResponses);
        }
    }

    @AfterEach
    void cleanUp() {
        student1 = null;
        student2 = null;
        studentResponse = null;
        studentRequest = null;
    }
}