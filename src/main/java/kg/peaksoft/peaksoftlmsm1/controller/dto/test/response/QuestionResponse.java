package kg.peaksoft.peaksoftlmsm1.controller.dto.test.response;

import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Option;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Long id;
    private String questionTitle;
    private List<Option> options;
    private Test test;

}
