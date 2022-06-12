package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.TeacherEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.TeacherViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherResponse;
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
public class TeacherService {

    private final UserRepository userRepository;
    private final TeacherEditMapper teacherEditMapper;
    private final TeacherViewMapper teacherViewMapper;

    public TeacherResponse create(TeacherRequest request){
        User user = teacherEditMapper.mapToEntity(request);
        userRepository.save(user);
        log.info("Entity user save: {}", user.getFirstName());
        return teacherViewMapper.mapToResponse(user);
    }

    public TeacherResponse update(Long id, TeacherRequest request){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        teacherEditMapper.mapToUpdate(user.get(), request);
        log.info("Entity user updated: {}", id);
        return teacherViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public TeacherResponse getById(Long id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        log.info("Get entity user by id: {}", id);
        return teacherViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public TeacherResponse delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        userRepository.deleteById(id);
        log.info("Delete entity user by id: {}", id);
        return teacherViewMapper.mapToResponse(user);
    }
}
