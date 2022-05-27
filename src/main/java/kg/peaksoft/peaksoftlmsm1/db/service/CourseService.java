package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.CourseResponseAll;
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
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final TeacherService teacherService;

    public CourseResponce save(CourseRequest courseRequest){
        Course course = mapToEntity(courseRequest);
        courseRepository.save(course);
        return mapToResponse(course);
    }

    public CourseResponce update(Long id, CourseRequest courseRequest){
        Optional<Course> optional = Optional.ofNullable(courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id)));
        mapToUpdate(optional.get(), courseRequest);
        return mapToResponse(courseRepository.save(optional.get()));
    }

    public CourseResponce getById(Long id){
        return mapToResponse(courseRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Entity", "id", id)));
    }

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public CourseResponce delete(Long id){
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        courseRepository.deleteById(id);
        return mapToResponse(course);
    }

    public List<Course> findCoursesByUser(String username){
        List<Course> courseList = courseRepository.findAllByUsername(username);
        return courseList;
    }

    public CourseResponseAll getAllCourses(int page, int size){
        CourseResponseAll courseResponseAll = new CourseResponseAll();
        Pageable pageable = PageRequest.of(page-1, size);
        courseResponseAll.setCourseResponses(map(courseRepository.findAllBy(pageable)));
        return courseResponseAll;
    }

    public Course mapToEntity(CourseRequest courseRequest) {
        Course course = new Course();
        List<User> userList = new ArrayList<>();
        course.setImage(courseRequest.getImage());
        course.setNameCourse(courseRequest.getNameCourse());
        course.setStartCourse(courseRequest.getStartCourse());
        course.setDescription(courseRequest.getDescription());
        User user = userRepository.findById(courseRequest.getUser()).get();
        userList.add(user);
        course.setUsers(userList);
        return course;
    }

    public Course mapToUpdate(Course course, CourseRequest courseRequest) {
        List<User> userList = new ArrayList<>();
        course.setImage(courseRequest.getImage());
        course.setNameCourse(courseRequest.getNameCourse());
        course.setStartCourse(courseRequest.getStartCourse());
        course.setDescription(courseRequest.getDescription());
        User user = userRepository.findById(courseRequest.getUser()).get();
        userList.add(user);
        course.setUsers(userList);
        return course;
    }

    public CourseResponce mapToResponse(Course course){
        return CourseResponce.builder()
                .id(course.getId())
                .image(course.getImage())
                .nameCourse(course.getNameCourse())
                .startCourse(course.getStartCourse())
                .description(course.getDescription())
                .build();
    }

    public List<CourseResponce> map(List<Course> courseList){
        List<CourseResponce> response = new ArrayList<>();
        for(Course course: courseList){
            response.add(mapToResponse(course));
        }
        return response;
    }

}
