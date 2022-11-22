package kg.peaksoft.peaksoftlmsm1.controller.dto.test.response;

import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResponse {

    private Long id;
    private String name;
    private List<Question> questionList;

}
