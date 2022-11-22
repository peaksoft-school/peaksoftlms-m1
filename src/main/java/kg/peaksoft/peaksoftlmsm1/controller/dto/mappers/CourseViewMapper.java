package kg.peaksoft.peaksoftlmsm1.controller.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseByIdForTeacher;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseForLesson;
import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponseForStudentLesson;
import kg.peaksoft.peaksoftlmsm1.db.entity.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponse mapToResponse(Course course){
        if (course == null) {
            return null;
        }
        CourseResponse responce = new CourseResponse();
        if (course.getId() != null) {
            responce.setId(course.getId());
        }
        responce.setNameCourse(course.getNameCourse());
        responce.setDescription(course.getDescription());
        responce.setStartCourse(course.getStartCourse());
        responce.setUsers(course.getUsers());
        responce.setGroups(course.getGroups());
        return responce;
    }

    public List<CourseResponse> map(List<Course> courseList){
        List<CourseResponse> response = new ArrayList<>();
        for(Course course: courseList){
            response.add(mapToResponse(course));
        }
        return response;
    }

    public CourseResponseByIdForTeacher toCourseToStudent(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponseByIdForTeacher responce = new CourseResponseByIdForTeacher();
        if (course.getId() != null) {
            responce.setId(course.getId());
            responce.setUsers(course.getUsers());
        }
        return responce;
    }

    public CourseResponseForLesson toCourseByLessons(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponseForLesson responce = new CourseResponseForLesson();
        if (course.getId() != null) {
            responce.setId(course.getId());
            responce.setLessons(course.getLessons());
        }
        return responce;
    }

    public CourseResponseForStudentLesson toCourseByStudentLessons(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponseForStudentLesson response = new CourseResponseForStudentLesson();
        if (course.getId() != null) {
            response.setId(course.getId());
            response.setLessons(course.getLessons());
        }
        return response;
    }

}
