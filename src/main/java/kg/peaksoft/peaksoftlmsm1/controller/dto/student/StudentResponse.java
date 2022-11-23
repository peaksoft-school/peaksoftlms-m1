package kg.peaksoft.peaksoftlmsm1.controller.dto.student;

import kg.peaksoft.peaksoftlmsm1.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsm1.db.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Group> group;
    private String password;
    private StudyFormat studyFormat;

}