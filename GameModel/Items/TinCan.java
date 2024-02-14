package GameModel.Items;

/**
 * Class TinCan.
 *__________________________
 * This is the Class that represents the TinCan Item.
 *
 */
public class TinCan extends Item{

    /**
     * TinCan class Constructor
     * __________________________
     */
    public TinCan() {
        super("Tin Can","Pretty useless, tin can, don't try to use it!");
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
        return 1.0;
    }

}
