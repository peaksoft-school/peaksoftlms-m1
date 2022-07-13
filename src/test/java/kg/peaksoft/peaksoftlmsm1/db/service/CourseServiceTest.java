package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.api.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.api.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.CourseEditMapper;
import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.CourseViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsm1.db.entity.Course;
import kg.peaksoft.peaksoftlmsm1.db.entity.Group;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class CourseServiceTest {

    @Autowired
    @InjectMocks
    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseEditMapper courseEditMapper;
    @Mock
    private CourseViewMapper courseViewMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private GroupRepository groupRepository;

    private Course course1;
    private Course course2;
    List<Course> courseList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        courseList = new ArrayList<>();
        course1 = new Course(1L, "image", "Java", null,"Nine",null,null,null);
        course2 = new Course(2L, "image2", "Java2", null,"Nine2",null,null,null);
        courseList.add(course1);
        courseList.add(course2);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save Courses")
    void shouldSaveCourse() {
        CourseRequest courseRequest = new CourseRequest("image", "Java", null, "Nine", null, null);

        Mockito.when(courseEditMapper.mapToEntity(courseRequest)).thenReturn(course1);
        courseService.save(courseRequest);

        Mockito.verify(courseRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Course.class));

    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should add student to Course by id")
    void addStudentToCourse() {
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));

        User user1 = new User(1L, "Student", "LastName", "password", "89997896767", "student@gmail.com",
                null,null,null,true,null,null,null);

        course1.setUsers(user1);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        CourseResponce courseResponce = new CourseResponce(1L,"image","Java", null,"Nine",user1,null,null);

        Mockito.when(courseViewMapper.mapToResponse(courseRepository.save(course1))).thenReturn(courseResponce);

        courseService.addStudentToCourse(1L,1L);

    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should update Course by id")
    void shouldUpdateCourseById() {
        course1.setNameCourse("JavaJava");
        CourseRequest courseRequest = new CourseRequest("image", "JavaJava", null, "Nine", null, null);

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));
        Mockito.when(courseEditMapper.mapToUpdate(course1, courseRequest)).thenReturn(course1);

        courseService.update(1L, courseRequest);

        Mockito.verify(courseRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Course.class));
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete Course by id")
    void deleteCourseById() {

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));
        courseService.delete(course1.getId());
    }

    @Test
    @Order(5)
    @DisplayName("JUnit test for {4} should get all Courses")
    void shouldGetAllCourses() {

        Mockito.when(courseService.getAll()).thenReturn(courseList);
        courseList = courseService.getAll();

        assertEquals(courseList, courseList);
        Mockito.verify(courseRepository, Mockito.times(1)).findAll();

    }

    @Test
    @Order(6)
    @DisplayName("JUnit test for {6} should add Group to Course by id")
    void addGroupToCourse() {
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));

        Group group1 = new Group(1L,"image","Java group",null,"Nine",null,null);

        course1.setGroups(group1);
        Mockito.when(groupRepository.findById(1L)).thenReturn(Optional.of(group1));

        CourseResponce courseResponce = new CourseResponce(1L,"image","Java", null,"Nine",null,group1,null);

        Mockito.when(courseViewMapper.mapToResponse(courseRepository.save(course1))).thenReturn(courseResponce);

        courseService.addGroupToCourse(1L,1L);
    }

    @Test
    @Order(7)
    @DisplayName("JUnit test for {7} should find Course by id")
    void shouldFindCourseById() {

        CourseResponce courseResponce = new CourseResponce(1L,"image","Java",null,"Nine",null,null,null);

        Mockito.when(courseRepository.findById(2L)).thenReturn(Optional.of(course1));
        Mockito.when(courseViewMapper.mapToResponse(Mockito.any(Course.class))).thenReturn(courseResponce);

        CourseResponce actualCourseResponse = courseService.getById(2L);

        Assertions.assertThat(actualCourseResponse.getId()).isEqualTo(courseResponce.getId());
        Assertions.assertThat(actualCourseResponse.getNameCourse()).isEqualTo(courseResponce.getNameCourse());

    }

    @Test
    @Order(8)
    @DisplayName("JUnit test for {8} should Lessons by Course id")
    void getLessonsByCourseId() {
        Lesson lesson1 = new Lesson(1L,"Java",null,null,null,null,course1,null);
        Lesson lesson2 = new Lesson(2L,"Java",null,null,null,null,course1,null);
        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson1);
        lessonList.add(lesson2);
        course1.setLessons(lessonList);
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));

        courseService.getLessonsByCourseId(1L);

    }

    @Test
    @Order(9)
    @DisplayName("JUnit test for {9} should Student Lessons by Course id")
    void getStudentLessonsByCourseId() {
        Lesson lesson1 = new Lesson(1L,"Java",null,null,null,null,course1,null);
        Lesson lesson2 = new Lesson(2L,"Java",null,null,null,null,course1,null);
        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson1);
        lessonList.add(lesson2);
        User user1 = new User(1L,"Student","LastName","password",
                "89996786767","student@gmail.com",null, StudyFormat.ONLINE,null,true,null,null,null);
        course1.setLessons(lessonList);
        course1.setUsers(user1);
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));
        courseService.getStudentLessonsByCourseId(1L);

    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> courseService.delete(course1.getId()));
        assertThrows(ResourceNotFoundException.class, () -> courseService.getByCourseId(course1.getId()));
    }

    @AfterEach
    public void tearDown() {
        course1 = course2 = null;
        courseList = null;
    }

}