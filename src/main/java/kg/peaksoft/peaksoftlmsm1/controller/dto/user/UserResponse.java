package kg.peaksoft.peaksoftlmsm1.controller.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private LocalDateTime created;

}
