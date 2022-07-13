package kg.peaksoft.peaksoftlmsm1.api.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.api.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TeacherEditMapper {

    public User mapToEntity(TeacherRequest teacherRequest){
        if (teacherRequest == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(teacherRequest.getFirstName());
        user.setLastName(teacherRequest.getLastName());
        user.setEmail(teacherRequest.getEmail());
        user.setPassword(teacherRequest.getPassword());
        user.setPhoneNumber(teacherRequest.getPhoneNumber());
        user.setSpecialization(teacherRequest.getSpecialization());
        user.setCreated(LocalDateTime.now());
        user.setRoles(teacherRequest.getRole());
        return user;
    }

    public User mapToUpdate(User user, TeacherRequest teacherRequest){
        user.setFirstName(teacherRequest.getFirstName());
        user.setLastName(teacherRequest.getLastName());
        user.setEmail(teacherRequest.getEmail());
        user.setPassword(teacherRequest.getPassword());
        user.setPhoneNumber(teacherRequest.getPhoneNumber());
        user.setSpecialization(teacherRequest.getSpecialization());
        user.setCreated(LocalDateTime.now());
        return user;
    }

}
