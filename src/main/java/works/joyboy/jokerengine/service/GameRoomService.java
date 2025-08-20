package works.joyboy.jokerengine.service;

import works.joyboy.jokerengine.exception.GeneralConflictException;
import works.joyboy.jokerengine.exception.GeneralNotFoundException;
import works.joyboy.jokerengine.model.CreateGameRoomRequest;
import works.joyboy.jokerengine.model.GameRoom;

import java.util.List;

public interface GameRoomService {

    GameRoom create(CreateGameRoomRequest request) throws GeneralNotFoundException;

    GameRoom get(long roomId) throws GeneralNotFoundException;

    List<GameRoom> getAll();

    GameRoom addPlayerToRoom(long roomId, long playerId) throws GeneralNotFoundException, GeneralConflictException;

    void close(long roomId) throws GeneralNotFoundException;
}
