package works.joyboy.jokerengine.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessages {
    NOT_FOUND("Entity not found"),
    CONFLICTING_ENTITY("Entity caused conflict");

    private String value;

    ExceptionMessages(String value) {
        this.value = value;
    }
}
