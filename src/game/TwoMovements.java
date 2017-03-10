package game;

/**
 * Pair of movements, first one white and then a black one
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class TwoMovements {
    private Movement whites;
    private Movement blacks;

    /**
     * Getter.
     *
     * @return white movement.
     */
    public Movement getWhites() {
        return whites;
    }

    /**
     * Getter.
     *
     * @return black movement .
     */
    public Movement getBlacks() {
        return blacks;
    }

    /**
     * Setter.
     *
     * @param whites movement of whites.
     */
    public void setWhites(Movement whites) {
        this.whites = whites;
    }

    /**
     * Setter.
     *
     * @param blacks movement of blacks.
     */
    public void setBlacks(Movement blacks) {
        this.blacks = blacks;
    }
}
