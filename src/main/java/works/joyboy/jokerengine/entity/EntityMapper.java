package works.joyboy.jokerengine.entity;

import works.joyboy.jokerengine.model.GameRoom;
import works.joyboy.jokerengine.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class EntityMapper {

    public static List<GameRoom> toDto(List<GameRoomEntity> gameRoomEntities) {
        return gameRoomEntities.stream().map(EntityMapper::toDto).collect(Collectors.toList());
    }

    public static GameRoom toDto(GameRoomEntity entity) {
        return new GameRoom(
                entity.getId(),
                entity.getType(),
                entity.getPlayers()
                        .stream()
                        .map(EntityMapper::toDto)
                        .collect(Collectors.toList()),
                entity.isInPairs()
        );
    }

    public static Player toDto(PlayerEntity e) {
        return new Player(e.getId(), e.getUsername());
    }
}