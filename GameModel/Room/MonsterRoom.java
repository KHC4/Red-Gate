package GameModel.Room;
import GameModel.*;
import GameModel.Enemy.Enemy;

/**
 * Interface MonsterRoom.
 *__________________________
 * This is the Interface that dictates how rooms with monsters should act.
 *
 */
public interface MonsterRoom extends Room{

    /**
     * addMonster
     * __________________________
     * Adds a Monster to the room.
     *
     */
    abstract void addMonster(Enemy monster);

    /**
     * removeMonster
     * __________________________
     * Removes a Monster from the room.
     *
     */
    abstract void removeMonster(Enemy monster);
}
