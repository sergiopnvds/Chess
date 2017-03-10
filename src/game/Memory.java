package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Save the movements done during the game
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class Memory {
    /**
     * Historic of the movements done in pairs.
     * Each position is a pair of whites-blacks.
     */
    private List<TwoMovements> movements = new ArrayList<TwoMovements>();

    /**
     * Creates an empty space to save a new pair of movements.
     *
     * @param two place to the pair of movements.
     */
    public void add(TwoMovements two) {
        movements.add(two);
    }

    /**
     * Getter.
     *
     * @return List of pair of movements.
     */
    public List<TwoMovements> getMovements() {
        return movements;
    }

    /**
     * @return Movements done till current moment.
     */
    public String toString() {
        StringBuilder history = new StringBuilder();
        for (int i = 0; i < movements.size(); i++) {
            TwoMovements two = movements.get(i);
            history.append(String.format("%3d.", i + 1));
            Movement whites = two.getWhites();
            if (whites != null) {
                history.append(String.format(" %s", whites));
            }
            Movement blacks = two.getBlacks();
            if (blacks != null) {
                history.append(String.format(" %s", blacks));
            }
            history.append("\n");
        }
        return history.toString();
    }
}
