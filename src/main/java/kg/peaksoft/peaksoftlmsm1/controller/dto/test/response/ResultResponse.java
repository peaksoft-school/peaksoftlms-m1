package kg.peaksoft.peaksoftlmsm1.controller.dto.test.response;

import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultResponse {

    private Long id;
    private User user;
    private Double correctAnswers;
    private Double incorrectAnswers;
    private String percentToResult;

}
