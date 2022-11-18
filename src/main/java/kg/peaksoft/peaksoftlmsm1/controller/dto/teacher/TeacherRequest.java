package kg.peaksoft.peaksoftlmsm1.controller.dto.teacher;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.Role;
import kg.peaksoft.peaksoftlmsm1.db.enums.Specialization;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeacherRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Specialization specialization;
    private List<Role> role;

}