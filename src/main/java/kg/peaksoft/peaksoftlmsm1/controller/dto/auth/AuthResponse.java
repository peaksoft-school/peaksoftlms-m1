package kg.peaksoft.peaksoftlmsm1.controller.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthResponse {

    private String jwtToken;
    private String message;
    private Set<String> authorities;

}
