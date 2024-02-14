package GameModel.Items;

/**
 * Class IronCan.
 *__________________________
 * This is the Class that represents the IronCan item.
 *
 */
public class IronCan extends Item{

    /**
     * IronCan Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public IronCan() {
        super("Iron Can", "Pretty useless, iron can, don't try to use it!");
    }

    /**
     * use
     * __________________________
     * Does nothing
     *
     * @return 0
     */
    @Override
    public double use() {
        return 0.0;
    }
}
