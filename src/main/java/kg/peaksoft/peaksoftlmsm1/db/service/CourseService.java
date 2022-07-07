package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.*;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.CourseEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.CourseViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.db.responseAll.CourseResponseAll;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseEditMapper courseEditMapper;
    private final CourseViewMapper courseViewMapper;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public CourseResponse save(CourseRequest courseRequest) {
        Course course = courseEditMapper.mapToEntity(courseRequest);
        courseRepository.save(course);
        log.info("Entity course save: {}", course.getNameCourse());
        return courseViewMapper.mapToResponse(course);
    }

    public CourseResponse update(Long id, CourseRequest courseRequest) {
        Optional<Course> optional = Optional.ofNullable(courseRepository.findById(id).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        courseEditMapper.mapToUpdate(optional.get(), courseRequest);
        log.info("Entity course updated: {}", id);
        return courseViewMapper.mapToResponse(courseRepository.save(optional.get()));
    }

    public CourseResponse getById(Long id) {
        log.info("Get entity course by id: {}", id);
        return courseViewMapper.mapToResponse(courseRepository.findById(id).orElseThrow(()
                -> {
            log.error("Entity course with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public void delete(Long id) {
        courseViewMapper.mapToResponse(courseRepository.findById(id).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        courseRepository.deleteById(id);
        log.info("Delete entity course by id: {}", id);
    }

    public CourseResponseAll getAllCourses(int page, int size) {
        CourseResponseAll courseResponseAll = new CourseResponseAll();
        Pageable pageable = PageRequest.of(page - 1, size);
        courseResponseAll.setCourseResponses(courseViewMapper.map(courseRepository.findAllBy(pageable)));
        return courseResponseAll;
    }

    public CourseResponse addStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", courseId);
            throw new ResourceNotFoundException("Entity", "id", courseId);
        });
        User user = userRepository.findById(studentId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", courseId);
            throw new ResourceNotFoundException("Entity", "id", studentId);
        });
        course.setUsers(user);
        return courseViewMapper.mapToResponse(courseRepository.save(course));
    }

    public CourseResponse addGroupToCourse(Long courseId, Long groupId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", courseId);
            throw new ResourceNotFoundException("Entity", "id", courseId);
        });
        Group group = groupRepository.findById(groupId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", courseId);
            throw new ResourceNotFoundException("Entity", "id", groupId);
        });
        course.setGroups(group);
        return courseViewMapper.mapToResponse(courseRepository.save(course));
    }

    public CourseResponseByIdForTeacher getByCourseId(Long courseId) {
        log.info("Get entity course by id: {}", courseId);
        return courseViewMapper.toCourseToStudent(courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", courseId);
            throw new ResourceNotFoundException("Entity", "id", courseId);
        }));
    }

    public CourseResponseForLesson getLessonsByCourseId(Long courseId) {
        log.info("Get entity course by id: {}", courseId);
        return courseViewMapper.toCourseByLessons(courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", courseId);
            throw new ResourceNotFoundException("Entity", "id", courseId);
        }));
    }

    public CourseResponseForStudentLesson getStudentLessonsByCourseId(Long courseId) {
        log.info("Get entity course by id: {}", courseId);
        return courseViewMapper.toCourseByStudentLessons(courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Entity course with id = {} does not exists in database", courseId);
            throw new ResourceNotFoundException("Entity", "id", courseId);
        }));
    }

}
