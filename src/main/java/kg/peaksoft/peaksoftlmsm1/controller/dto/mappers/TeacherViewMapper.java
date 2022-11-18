package kg.peaksoft.peaksoftlmsm1.controller.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherViewMapper {

    public TeacherResponse mapToResponse(User user){
        if (user == null) {
            return null;
        }
        TeacherResponse teacherResponse = new TeacherResponse();
        if (user.getId() != null) {
            teacherResponse.setId(user.getId());
        }
        teacherResponse.setFirstName(user.getFirstName());
        teacherResponse.setLastName(user.getLastName());
        teacherResponse.setEmail(user.getEmail());
        teacherResponse.setPassword(user.getPassword());
        teacherResponse.setSpecialization(user.getSpecialization());
        teacherResponse.setPhoneNumber(user.getPhoneNumber());
        return teacherResponse;
    }

    public List<TeacherResponse> map(List<User> userList){
        List<TeacherResponse> responses = new ArrayList<>();
        for(User user: userList){
            responses.add(mapToResponse(user));
        }
        return responses;
    }
}
