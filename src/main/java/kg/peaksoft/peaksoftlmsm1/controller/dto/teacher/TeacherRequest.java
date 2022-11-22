package kg.peaksoft.peaksoftlmsm1.controller.dto.teacher;

import kg.peaksoft.peaksoftlmsm1.db.entity.Role;
import kg.peaksoft.peaksoftlmsm1.db.enums.Specialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Specialization specialization;
    private List<Role> role;

}