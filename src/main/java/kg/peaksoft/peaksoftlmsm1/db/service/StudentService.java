package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.StudentEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.StudentViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;
    private final StudentEditMapper studentEditMapper;
    private final StudentViewMapper studentViewMapper;

    public StudentResponse create(StudentRequest request){
        User user = studentEditMapper.mapToEntity(request);
        userRepository.save(user);
        log.info("Entity user save: {}", user.getFirstName());
        return studentViewMapper.mapToResponse(user);
    }

    public StudentResponse update(Long id, StudentRequest request){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        studentEditMapper.mapToUpdate(user.get(), request);
        log.info("Entity user updated: {}", id);
        return studentViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public StudentResponse getById(Long id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        log.info("Get entity user by id: {}", id);
        return studentViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public StudentResponse delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        userRepository.deleteById(id);
        log.info("Delete entity user by id: {}", id);
        return studentViewMapper.mapToResponse(user);
    }
}
