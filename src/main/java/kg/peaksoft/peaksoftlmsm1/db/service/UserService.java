package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.UserEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.UserViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.request.UserRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.response.UserResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserEditMapper userEditMapper;
    private final UserViewMapper userViewMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserResponse create(UserRequest userRequest){
        User user = userEditMapper.mapToEntity(userRequest);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        log.info("Entity group save: {}", user.getFirstName());
        return userViewMapper.mapToResponse(user);
    }

    public UserResponse update(Long id, UserRequest userRequest){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity group with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        userEditMapper.mapToUpdate(user.get(), userRequest);
        log.info("Entity group updated: {}", id);
        return userViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public UserResponse getById(Long id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity group with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        log.info("Get entity group by id: {}", id);
        return userViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public List<User> getAll(){
        log.info("Entity group get all: {}");
        return userRepository.findAll();
    }

    public UserResponse delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity group with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        userRepository.deleteById(id);
        log.info("Delete entity group by id: {}", id);
        return userViewMapper.mapToResponse(user);
    }
}
