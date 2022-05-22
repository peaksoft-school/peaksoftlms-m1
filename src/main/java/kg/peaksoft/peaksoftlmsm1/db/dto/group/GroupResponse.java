package kg.peaksoft.peaksoftlmsm1.db.dto.group;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.model.Course;
import kg.peaksoft.peaksoftlmsm1.model.Student;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupResponse {

    private Long id;
    private String group_name;
    private LocalDate date_of_start;
    private String description;
    private Course courses;
    private List<Student> student;
}
