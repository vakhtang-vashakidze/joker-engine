package works.joyboy.jokerengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GeneralNotFoundException extends Exception {
    public GeneralNotFoundException(String message) {
        super(message);
    }
}
