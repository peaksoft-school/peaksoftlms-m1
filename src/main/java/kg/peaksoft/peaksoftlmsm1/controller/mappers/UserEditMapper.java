package kg.peaksoft.peaksoftlmsm1.controller.mappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.user.UserRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserEditMapper {

    public User mapToEntity(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
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

}
