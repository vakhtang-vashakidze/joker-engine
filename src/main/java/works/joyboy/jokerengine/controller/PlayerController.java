package works.joyboy.jokerengine.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import works.joyboy.jokerengine.exception.GeneralConflictException;
import works.joyboy.jokerengine.exception.GeneralNotFoundException;
import works.joyboy.jokerengine.model.CreatePlayerRequest;
import works.joyboy.jokerengine.model.Player;
import works.joyboy.jokerengine.service.PlayerService;

import java.util.List;

//TODO this is temporary to manage players from engine itself (will need user creation/management and JWT).
@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody CreatePlayerRequest request) throws GeneralConflictException {
        return ResponseEntity.ok(playerService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable long id) throws GeneralNotFoundException {
        return ResponseEntity.ok(playerService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Player>> listPlayers() {
        return ResponseEntity.ok(playerService.list());
    }
}
