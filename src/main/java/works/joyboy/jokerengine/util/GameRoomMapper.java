package works.joyboy.jokerengine.util;

import works.joyboy.jokerengine.entity.GameRoomEntity;
import works.joyboy.jokerengine.entity.PlayerEntity;
import works.joyboy.jokerengine.model.GameRoom;
import works.joyboy.jokerengine.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class GameRoomMapper {

    public static List<GameRoom> toDto(List<GameRoomEntity> gameRoomEntities) {
        return gameRoomEntities.stream().map(GameRoomMapper::toDto).collect(Collectors.toList());
    }

    public static GameRoom toDto(GameRoomEntity entity) {
        return new GameRoom(
                entity.getId(),
                entity.getType(),
                entity.getPlayers()
                        .stream()
                        .map(GameRoomMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    public static Player toDto(PlayerEntity entity) {
        return new Player(
                entity.getId(),
                entity.getUsername()
        );
    }
}