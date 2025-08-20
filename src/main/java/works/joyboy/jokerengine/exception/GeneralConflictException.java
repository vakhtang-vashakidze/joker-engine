package works.joyboy.jokerengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GeneralConflictException extends Exception {
    public GeneralConflictException(String message) {
        super(message);
    }
}
