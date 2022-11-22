package kg.peaksoft.peaksoftlmsm1.controller.dto.responseAll;

import kg.peaksoft.peaksoftlmsm1.controller.dto.course.CourseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseAll {

    private List<CourseResponse> courseResponses;
}
