package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherResponce;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
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
public class TeacherService {

    private final UserRepository userRepository;

    public TeacherResponce create(TeacherRequest request){
        User user = mapToEntity(request);
        userRepository.save(user);
        return mapToResponse(user);
    }

    public TeacherResponce update(Long id, TeacherRequest request){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        mapToUpdate(user.get(), request);
        return mapToResponse(userRepository.save(user.get()));
    }

    public TeacherResponce getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        return mapToResponse(userRepository.save(user.get()));
    }

    public TeacherResponce delete(Long id){
        User group = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        userRepository.deleteById(id);
        return mapToResponse(group);
    }

    public User mapToEntity(TeacherRequest teacherRequest){
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

    public TeacherResponce mapToResponse(User user){
        return TeacherResponce.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .specialization(user.getSpecialization())
                .build();
    }

    public List<TeacherResponce> map(List<User> userList){
        List<TeacherResponce> responses = new ArrayList<>();
        for(User user: userList){
            responses.add(mapToResponse(user));
        }
        return responses;
    }
}
