package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

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


    public Course mapToEntity(CourseRequest courseRequest) {
        Course course = new Course();
        course.setNameCourse(courseRequest.getName_course());
        course.setStartCourse(courseRequest.getStart_course());
        course.setDescription(courseRequest.getDescription());
        return course;
    }

    public Course mapToUpdate(Course course, CourseRequest courseRequest) {
        course.setNameCourse(courseRequest.getName_course());
        course.setStartCourse(courseRequest.getStart_course());
        course.setDescription(courseRequest.getDescription());
        return course;
    }

    public CourseResponce mapToResponse(Course course){
        return CourseResponce.builder()
                .id(course.getId())
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
