package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.mappers.edit.TeacherEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.view.TeacherViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;
    private final TeacherEditMapper teacherEditMapper;
    private final TeacherViewMapper teacherViewMapper;

    public TeacherResponse create(TeacherRequest request) {
        User user = teacherEditMapper.mapToEntity(request);
        userRepository.save(user);
        log.info("Entity user save: {}", user.getFirstName());
        return teacherViewMapper.mapToResponse(user);
    }

    public TeacherResponse update(Long id, TeacherRequest request) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        teacherEditMapper.mapToUpdate(user.orElseThrow(NoSuchElementException::new), request);
        log.info("Entity user updated: {}", id);
        return teacherViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public TeacherResponse getById(Long id) {
        log.info("Get entity teacher by id: {}", id);
        return teacherViewMapper.mapToResponse(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity teacher with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public TeacherResponse delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        userRepository.deleteById(id);
        log.info("Delete entity user by id: {}", id);
        return teacherViewMapper.mapToResponse(user);
    }

    public List<TeacherResponse> getAll() {
        log.info("Entity teacher get all");
        return teacherViewMapper.map(userRepository.findAll());
    }

}
