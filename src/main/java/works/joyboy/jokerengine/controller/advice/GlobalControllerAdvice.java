package works.joyboy.jokerengine.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import works.joyboy.jokerengine.exception.GeneralConflictException;
import works.joyboy.jokerengine.exception.GeneralNotFoundException;
import works.joyboy.jokerengine.model.GeneralResponse;

import javax.naming.ConfigurationException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(GeneralNotFoundException.class)
    public ResponseEntity<GeneralResponse> handleNotFound(GeneralNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(GeneralResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @ExceptionHandler(ConfigurationException.class)
    public ResponseEntity<GeneralResponse> handleConflict(GeneralConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(GeneralResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.CONFLICT.value())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GeneralResponse.builder()
                        .message("Unexpected error: " + ex.getMessage())
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build());
    }
}
