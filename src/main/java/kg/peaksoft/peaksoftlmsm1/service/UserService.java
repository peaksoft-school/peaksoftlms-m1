package kg.peaksoft.peaksoftlmsm1.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.request.UserRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.response.UserResponse;
import kg.peaksoft.peaksoftlmsm1.entity.securityEntity.User;
import kg.peaksoft.peaksoftlmsm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserResponse create(UserRequest userRequest){
        User user = mapToEntity(userRequest);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse update(Long id, UserRequest userRequest){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        mapToUpdate(user.get(), userRequest);
        return mapToResponse(userRepository.save(user.get()));
    }

    public UserResponse getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println(user + "with id not found");
        }
        return mapToResponse(userRepository.save(user.get()));
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public UserResponse delete(Long id){
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return mapToResponse(user);
    }

    //=========================EDIT MAPPER==================
    public User mapToEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setCreated(LocalDateTime.now());
        return user;
    }

    public User mapToUpdate(User user, UserRequest userRequest){
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setCreated(LocalDateTime.now());
        return user;
    }

    //========================VIEW MAPPER==================
    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.isActive())
                .created(user.getCreated())
                .build();
    }

    public List<UserResponse> map(List<User> userList){
        List<UserResponse> response = new ArrayList<>();
        for(User user: userList){
            response.add(mapToResponse(user));
        }
        return response;
    }

}
