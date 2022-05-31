package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.CourseEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.CourseViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.CourseResponseAll;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseEditMapper courseEditMapper;
    private final CourseViewMapper courseViewMapper;

    public CourseResponce save(CourseRequest courseRequest){
        Course course = courseEditMapper.mapToEntity(courseRequest);
        courseRepository.save(course);
        return courseViewMapper.mapToResponse(course);
    }

    public CourseResponce update(Long id, CourseRequest courseRequest){
        Optional<Course> optional = Optional.ofNullable(courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id)));
        courseEditMapper.mapToUpdate(optional.get(), courseRequest);
        return courseViewMapper.mapToResponse(courseRepository.save(optional.get()));
    }

    public CourseResponce getById(Long id){
        return courseViewMapper.mapToResponse(courseRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Entity", "id", id)));
    }

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public CourseResponce delete(Long id){
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        courseRepository.deleteById(id);
        return courseViewMapper.mapToResponse(course);
    }

    public List<Course> findCoursesByUser(String username){
        List<Course> courseList = courseRepository.findAllByUsername(username);
        return courseList;
    }

    public CourseResponseAll getAllCourses(int page, int size){
        CourseResponseAll courseResponseAll = new CourseResponseAll();
        Pageable pageable = PageRequest.of(page-1, size);
        courseResponseAll.setCourseResponses(courseViewMapper.map(courseRepository.findAllBy(pageable)));
        return courseResponseAll;
    }

}
