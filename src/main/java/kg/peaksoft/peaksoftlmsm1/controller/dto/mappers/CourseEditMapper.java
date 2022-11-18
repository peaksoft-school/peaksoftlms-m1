package kg.peaksoft.peaksoftlmsm1.controller.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.Course;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseEditMapper {

    private final UserRepository userRepository;

    public Course mapToEntity(CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        Course course = new Course();
        List<User> userList = new ArrayList<>();
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
        course.setNameCourse(courseRequest.getNameCourse());
        course.setStartCourse(courseRequest.getStartCourse());
        course.setDescription(courseRequest.getDescription());
        User user = userRepository.findById(courseRequest.getUser()).get();
        userList.add(user);
        course.setUsers(userList);
        return course;
    }

}
