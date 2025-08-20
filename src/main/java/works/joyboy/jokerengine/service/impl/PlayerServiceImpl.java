package works.joyboy.jokerengine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import works.joyboy.jokerengine.entity.PlayerEntity;
import works.joyboy.jokerengine.exception.GeneralConflictException;
import works.joyboy.jokerengine.exception.GeneralNotFoundException;
import works.joyboy.jokerengine.model.CreatePlayerRequest;
import works.joyboy.jokerengine.model.Player;
import works.joyboy.jokerengine.repository.PlayerRepository;
import works.joyboy.jokerengine.service.PlayerService;
import works.joyboy.jokerengine.util.PlayerMapper;

import java.util.List;

import static works.joyboy.jokerengine.exception.ExceptionMessages.CONFLICTING_ENTITY;
import static works.joyboy.jokerengine.exception.ExceptionMessages.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public Player create(CreatePlayerRequest request) throws GeneralConflictException {
        if (playerRepository.existsByUsername(request.username())) {
            throw new GeneralConflictException(CONFLICTING_ENTITY.getValue());
        }
        PlayerEntity saved = playerRepository.save(PlayerEntity.builder().username(request.username()).build());
        return PlayerMapper.toDTO(saved);
    }

    public Player get(long id) throws GeneralNotFoundException {
        return PlayerMapper.toDTO(playerRepository.findById(id)
                .orElseThrow(() -> new GeneralNotFoundException(NOT_FOUND.getValue())));
    }

    public List<Player> list() {
        return playerRepository.findAll().stream().map(PlayerMapper::toDTO).toList();
    }
}
