package kg.peaksoft.peaksoftlmsm1.api.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.api.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentEditMapper {

    private final GroupRepository groupRepository;

    public User mapToEntity(StudentRequest studentRequest){
        if (studentRequest == null) {
            return null;
        }
        List<Group> groups = new ArrayList<>();
        User user = new User();

        user.setFirstName(studentRequest.getFirstName());
        user.setLastName(studentRequest.getLastName());
        user.setEmail(studentRequest.getEmail());
        user.setPassword(studentRequest.getPassword());
        user.setPhoneNumber(studentRequest.getPhoneNumber());
        Group group1 = groupRepository.findById(studentRequest.getGroup()).get();
        groups.add(group1);
        user.setGroups(groups);
        user.setStudyFormat(studentRequest.getStudyFormat());
        user.setCreated(LocalDateTime.now());
        user.setRoles(studentRequest.getRole());
        return user;
    }

    public User mapToUpdate(User user, StudentRequest studentRequest){
        List<Group> groups = new ArrayList<>();
        user.setFirstName(studentRequest.getFirstName());
        user.setLastName(studentRequest.getLastName());
        user.setEmail(studentRequest.getEmail());
        user.setPassword(studentRequest.getPassword());
        user.setPhoneNumber(studentRequest.getPhoneNumber());
        Group group1 = groupRepository.findById(studentRequest.getGroup()).get();
        groups.add(group1);
        user.setGroups(groups);
        user.setStudyFormat(studentRequest.getStudyFormat());
        user.setCreated(LocalDateTime.now());
        return user;
    }

}
