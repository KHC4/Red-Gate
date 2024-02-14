package GameModel.Enemy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyClassTest{
    @Test
    void testenemyHasDifferentBehaviours(){
        Giant giant = new Giant();
        Skeleton skeleton = new Skeleton();
        Zombie zombie = new Zombie();
        assertEquals(100,giant.getHealth());
        assertEquals(20,skeleton.getHealth());
        assertEquals(60,zombie.getHealth());
        assertEquals(20,giant.getWorth());
        assertEquals(5,skeleton.getWorth());
        assertEquals(13,zombie.getWorth());
    }
}