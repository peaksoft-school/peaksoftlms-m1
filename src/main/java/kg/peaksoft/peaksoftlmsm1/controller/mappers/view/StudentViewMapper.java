package kg.peaksoft.peaksoftlmsm1.controller.mappers.view;

import kg.peaksoft.peaksoftlmsm1.controller.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentViewMapper {

    public StudentResponse mapToResponse(User user){
        if (user == null) {
            return null;
        }
        StudentResponse studentResponse = new StudentResponse();
        if (user.getId() != null) {
            studentResponse.setId(user.getId());
        }
        studentResponse.setFirstName(user.getFirstName());
        studentResponse.setEmail(user.getEmail());
        studentResponse.setLastName(user.getLastName());
        studentResponse.setPhoneNumber(user.getPhoneNumber());
        studentResponse.setStudyFormat(user.getStudyFormat());
        studentResponse.setPassword(user.getPassword());
        studentResponse.setGroup(user.getGroups());
        return studentResponse;
    }

    public List<StudentResponse> map(List<User> userList){
        List<StudentResponse> responses = new ArrayList<>();
        for(User user: userList){
            responses.add(mapToResponse(user));
        }
        return responses;
    }
}
