package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.StudentEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.StudentViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;
    private final StudentEditMapper studentEditMapper;
    private final StudentViewMapper studentViewMapper;

    public StudentResponse create(StudentRequest request){
        User user = studentEditMapper.mapToEntity(request);
        userRepository.save(user);
        return studentViewMapper.mapToResponse(user);
    }

    public StudentResponse update(Long id, StudentRequest request){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        studentEditMapper.mapToUpdate(user.get(), request);
        return studentViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public StudentResponse getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        return studentViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public StudentResponse delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        userRepository.deleteById(id);
        return studentViewMapper.mapToResponse(user);
    }
}
