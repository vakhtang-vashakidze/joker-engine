package works.joyboy.jokerengine.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import works.joyboy.jokerengine.exception.GeneralConflictException;
import works.joyboy.jokerengine.exception.GeneralNotFoundException;
import works.joyboy.jokerengine.model.AddPlayerRequest;
import works.joyboy.jokerengine.model.CreateGameRoomRequest;
import works.joyboy.jokerengine.model.GameRoom;
import works.joyboy.jokerengine.model.GeneralResponse;
import works.joyboy.jokerengine.service.GameRoomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game-rooms")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class GameRoomController {

    private final GameRoomService gameRoomService;

    @PostMapping
    public ResponseEntity<GameRoom> createGameRoom(@Valid @RequestBody CreateGameRoomRequest request) throws GeneralNotFoundException {
        return ResponseEntity.ok(gameRoomService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GameRoom>> getAllGameRooms() {
        return ResponseEntity.ok(gameRoomService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameRoom> getGameRoom(@PathVariable long id) throws GeneralNotFoundException {
        return ResponseEntity.ok(gameRoomService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> closeGameRoom(@PathVariable long id) throws GeneralNotFoundException {
        gameRoomService.close(id);
        return ResponseEntity.ok(GeneralResponse.builder()
                .message("Game room closed")
                .code(HttpStatus.NO_CONTENT.value())
                .build());
    }

    @PostMapping("/{id}/players")
    public ResponseEntity<GameRoom> addPlayerToRoom(@PathVariable long id, @RequestBody AddPlayerRequest request) throws GeneralNotFoundException, GeneralConflictException {
        return ResponseEntity.ok(gameRoomService.addPlayerToRoom(id, request.playerId()));
    }
}
