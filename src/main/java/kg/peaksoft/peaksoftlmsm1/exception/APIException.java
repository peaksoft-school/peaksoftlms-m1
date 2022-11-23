package kg.peaksoft.peaksoftlmsm1.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIException extends RuntimeException {

    private HttpStatus status;
    private String message;

}
