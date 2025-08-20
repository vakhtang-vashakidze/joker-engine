package works.joyboy.jokerengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import works.joyboy.jokerengine.entity.GameRoomEntity;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoomEntity, Long> {
}
