package works.joyboy.jokerengine.model;

import lombok.Builder;

@Builder
public record GeneralResponse(String message, int code) {
}
