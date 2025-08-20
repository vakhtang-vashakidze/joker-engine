package works.joyboy.jokerengine.service;

import works.joyboy.jokerengine.exception.GeneralConflictException;
import works.joyboy.jokerengine.exception.GeneralNotFoundException;
import works.joyboy.jokerengine.model.CreatePlayerRequest;
import works.joyboy.jokerengine.model.Player;

import java.util.List;

public interface PlayerService {
    Player create(CreatePlayerRequest request) throws GeneralConflictException;

    Player get(long id) throws GeneralNotFoundException;

    List<Player> list();
}
