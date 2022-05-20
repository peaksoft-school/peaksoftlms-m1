package kg.peaksoft.peaksoftlmsm1.db.dto.response;

import kg.peaksoft.peaksoftlmsm1.entity.securityEntity.Role;
import kg.peaksoft.peaksoftlmsm1.entity.securityEntity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthMapper {

    public AuthResponse view(String token, String message, User user){

        var authResponse = new AuthResponse();
        if (user != null) {
            setAuthority(authResponse, user.getRoles());
        }
        authResponse.setJwtToken(token);
        authResponse.setMessage(message);
        return authResponse;
    }

    public void setAuthority(AuthResponse authResponse, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for(Role role: roles){
            authorities.add(role.getName());
        }
        authResponse.setAuthorities(authorities);
    }
}
