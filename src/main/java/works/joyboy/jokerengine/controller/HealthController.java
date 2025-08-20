package works.joyboy.jokerengine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import works.joyboy.jokerengine.model.GeneralResponse;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping(value = "/health")
    public ResponseEntity<GeneralResponse> health() {
        return ResponseEntity.ok(GeneralResponse.builder()
                .message("I'm healthy!")
                .code(HttpStatus.OK.value())
                .build());
    }
}
