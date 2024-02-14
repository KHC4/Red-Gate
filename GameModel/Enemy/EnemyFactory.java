package GameModel.Enemy;

import java.util.Random;
/**
 * Class EnemyFactory.
 *__________________________
 * This is the Class that generates enemies randomly when called for.
 *
 */
public class EnemyFactory {
    /**
     * createEnemt
     * __________________________
     * Creates and returns an enemy at random
     *
     * @return enemy: the created enemy
     */
    public Enemy createEnemy(){
        Random rand = new Random();
        int selected = rand.nextInt(10);
        Enemy enemy = null;

        if (selected < 2){
            enemy = new Giant();
        }else if (selected < 5){
            enemy = new Skeleton();
        }else{
            enemy = new Zombie();
        }
        return enemy;
    }
}
