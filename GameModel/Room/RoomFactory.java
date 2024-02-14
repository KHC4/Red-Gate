package GameModel.Room;

import GameModel.Enemy.EnemyFactory;
import GameModel.Items.ItemFactory;
import java.util.Random;

/**
 * Class RoomFactory.  Handles all the necessary tasks to create rooms at random.
 */
public class RoomFactory {

    /**
     * createRoom
     * __________________________
     * Creates a RegularRoom or HealingRoom randomly and returns it.
     * Adds up to 3 items in the generated room
     * Adds ceil(roomNum/4) monsters to the room to ramp up difficulty as the game goes on
     * @return Room: the generated room
     */
    public Room createRoom(int num){
        EnemyFactory enemyFactory = new EnemyFactory();
        ItemFactory itemFactory = new ItemFactory();
        Random rand = new Random();
        int selected = rand.nextInt(10);
        Room room = null;

        if (selected < 2){
            room = new HealingRoom(num);
        }else{
            room = new RegularRoom(num);
            int numberEnemies = Math.floorDiv(num, 4);
            for (int i = 0; i < numberEnemies; i++){
                ((RegularRoom) room).addMonster(enemyFactory.createEnemy());
            }
        }
        for (int i = 0; i < rand.nextInt(4); i++){
            room.addItem(itemFactory.createItem());
        }

        return room;
    }
}
