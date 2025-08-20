package works.joyboy.jokerengine.model;

import works.joyboy.jokerengine.entity.GameRoomType;

//TODO utilize owner later
public record CreateGameRoomRequest(GameRoomType type, long ownerId) {
}
