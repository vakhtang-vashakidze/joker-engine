package works.joyboy.jokerengine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import works.joyboy.jokerengine.entity.GameRoomEntity;
import works.joyboy.jokerengine.entity.GameRoomState;
import works.joyboy.jokerengine.entity.PlayerEntity;
import works.joyboy.jokerengine.exception.GeneralConflictException;
import works.joyboy.jokerengine.exception.GeneralNotFoundException;
import works.joyboy.jokerengine.model.CreateGameRoomRequest;
import works.joyboy.jokerengine.model.GameRoom;
import works.joyboy.jokerengine.repository.GameRoomRepository;
import works.joyboy.jokerengine.repository.PlayerRepository;
import works.joyboy.jokerengine.service.GameRoomService;
import works.joyboy.jokerengine.util.GameRoomMapper;

import java.util.List;
import java.util.Optional;

import static works.joyboy.jokerengine.exception.ExceptionMessages.CONFLICTING_ENTITY;
import static works.joyboy.jokerengine.exception.ExceptionMessages.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GameRoomServiceImpl implements GameRoomService {

    private final GameRoomRepository gameRoomRepository;
    private final PlayerRepository playerRepository;

    @Override
    public GameRoom create(CreateGameRoomRequest request) throws GeneralNotFoundException {
        PlayerEntity playerEntity = playerRepository.findById(request.ownerId()).orElseThrow(() -> new GeneralNotFoundException(NOT_FOUND.getValue()));
        GameRoomEntity room = GameRoomEntity.builder()
                .type(request.type())
                .state(GameRoomState.OPEN)
                .players(List.of(playerEntity))
                .build();
        room = gameRoomRepository.save(room);
        return GameRoomMapper.toDto(room);
    }

    @Override
    public GameRoom get(long roomId) throws GeneralNotFoundException {
        Optional<GameRoomEntity> gameRoom = gameRoomRepository.findById(roomId);
        if (gameRoom.isPresent()) {
            return GameRoomMapper.toDto(gameRoom.get());
        } else {
            throw new GeneralNotFoundException(NOT_FOUND.getValue());
        }
    }

    @Override
    public List<GameRoom> getAll() {
        List<GameRoomEntity> gameRooms = gameRoomRepository.findAll();
        return GameRoomMapper.toDto(gameRooms);
    }

    @Override
    public GameRoom addPlayerToRoom(long roomId, long playerId) throws GeneralNotFoundException, GeneralConflictException {
        GameRoomEntity gameRoom = gameRoomRepository.findById(roomId).orElseThrow(() -> new GeneralNotFoundException(NOT_FOUND.getValue()));
        PlayerEntity playerEntity = playerRepository.findById(playerId).orElseThrow(() -> new GeneralNotFoundException(NOT_FOUND.getValue()));
        if (gameRoom.getPlayers().contains(playerEntity)) {
            throw new GeneralConflictException(CONFLICTING_ENTITY.getValue());
        }
        gameRoom.getPlayers().add(playerEntity);
        gameRoom = gameRoomRepository.save(gameRoom);
        return GameRoomMapper.toDto(gameRoom);
    }

    @Override
    public void close(long roomId) throws GeneralNotFoundException {
        GameRoomEntity gameRoom = gameRoomRepository.findById(roomId).orElseThrow(() -> new GeneralNotFoundException(NOT_FOUND.getValue()));
        gameRoom.setState(GameRoomState.FINISHED);
        gameRoomRepository.save(gameRoom);
    }
}
