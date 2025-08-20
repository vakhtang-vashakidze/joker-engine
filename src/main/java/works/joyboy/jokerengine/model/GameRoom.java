package works.joyboy.jokerengine.model;

import works.joyboy.jokerengine.entity.GameRoomType;

import java.util.List;

public record GameRoom(long id, GameRoomType type, List<Player> players) {
}
