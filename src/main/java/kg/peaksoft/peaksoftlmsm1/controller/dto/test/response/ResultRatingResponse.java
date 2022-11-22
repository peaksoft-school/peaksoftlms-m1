package kg.peaksoft.peaksoftlmsm1.controller.dto.test.response;

import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultRatingResponse {

    private Long id;
    private User user;
    private String percentToResult;

}
