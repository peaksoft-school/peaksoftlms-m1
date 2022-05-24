package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public StudentResponse create(StudentRequest request){
        User user = mapToEntity(request);
        userRepository.save(user);
        return mapToResponse(user);
    }

    public StudentResponse update(Long id, StudentRequest request){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        mapToUpdate(user.get(), request);
        return mapToResponse(userRepository.save(user.get()));
    }

    public StudentResponse getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        return mapToResponse(userRepository.save(user.get()));
    }

    public StudentResponse delete(Long id){
        User group = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        userRepository.deleteById(id);
        return mapToResponse(group);
    }

    public User mapToEntity(StudentRequest studentRequest){
        List<Group> groups = new ArrayList<>();
        User user = new User();

        user.setFirstName(studentRequest.getFirstName());
        user.setLastName(studentRequest.getLastName());
        user.setEmail(studentRequest.getEmail());
        user.setPassword(studentRequest.getPassword());
        user.setPhoneNumber(studentRequest.getPhoneNumber());
        Group group1 = groupRepository.findById(studentRequest.getGroup().getId()).get();
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
        Group group1 = groupRepository.findById(studentRequest.getGroup().getId()).get();
        groups.add(group1);
        user.setGroups(groups);
        user.setStudyFormat(studentRequest.getStudyFormat());
        user.setCreated(LocalDateTime.now());
        return user;
    }

    public StudentResponse mapToResponse(User user){
        return StudentResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .group(user.getGroups())
                .studyFormat(user.getStudyFormat())
                .build();
    }

    public List<StudentResponse> map(List<User> userList){
        List<StudentResponse> responses = new ArrayList<>();
        for(User user: userList){
            responses.add(mapToResponse(user));
        }
        return responses;
    }
}
