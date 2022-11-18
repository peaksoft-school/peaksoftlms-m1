package kg.peaksoft.peaksoftlmsm1.controller.dto.responseAll;

import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponce;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseAll {

    private List<CourseResponce> courseResponses;
}
