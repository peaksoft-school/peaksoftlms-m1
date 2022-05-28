package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.TeacherEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.TeacherViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;
    private final TeacherEditMapper teacherEditMapper;
    private final TeacherViewMapper teacherViewMapper;


    public TeacherResponse create(TeacherRequest request){
        User user = teacherEditMapper.mapToEntity(request);
        userRepository.save(user);
        return teacherViewMapper.mapToResponse(user);
    }

    public TeacherResponse update(Long id, TeacherRequest request){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        teacherEditMapper.mapToUpdate(user.get(), request);
        return teacherViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public TeacherResponse getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        return teacherViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public TeacherResponse delete(Long id){
        User group = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        userRepository.deleteById(id);
        return teacherViewMapper.mapToResponse(group);
    }

}
