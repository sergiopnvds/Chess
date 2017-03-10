package game;

/**
 * A chess player
 *
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public interface Player {
    /**
     * The player make a decision.
     *
     * @param chessboard actual state of the game.
     * @param memory historical of movements.
     * @return movement chosen.
     */
    Movement getMovement(Chessboard chessboard, Memory memory);
}
