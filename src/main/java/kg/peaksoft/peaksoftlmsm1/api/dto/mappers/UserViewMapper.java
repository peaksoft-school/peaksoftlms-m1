package kg.peaksoft.peaksoftlmsm1.api.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.api.dto.response.UserResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserViewMapper {

    public UserResponse mapToResponse(User user){
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        if(user.getId() != null) {
            response.setId(user.getId());
        }
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setActive(user.isActive());
        response.setCreated(user.getCreated());
        return response;
    }

    public List<UserResponse> map(List<User> userList){
        List<UserResponse> response = new ArrayList<>();
        for(User user: userList){
            response.add(mapToResponse(user));
        }
        return response;
    }

}
