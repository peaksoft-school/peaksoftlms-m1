package kg.peaksoft.peaksoftlmsm1.db.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.course.CourseResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponce mapToResponse(Course course){
        if (course == null) {
            return null;
        }
        CourseResponce responce = new CourseResponce();
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

    public List<CourseResponce> map(List<Course> courseList){
        List<CourseResponce> response = new ArrayList<>();
        for(Course course: courseList){
            response.add(mapToResponse(course));
        }
        return response;
    }

}
