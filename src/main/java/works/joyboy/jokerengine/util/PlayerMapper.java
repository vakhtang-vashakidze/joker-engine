package works.joyboy.jokerengine.util;

import works.joyboy.jokerengine.entity.PlayerEntity;
import works.joyboy.jokerengine.model.Player;

public class PlayerMapper {

    public static Player toDTO(PlayerEntity e) {
        return new Player(e.getId(), e.getUsername());
    }
}
