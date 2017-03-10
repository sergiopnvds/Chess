

import pieces.Side;
import javax.swing.*;
import game.Chessboard;
import game.Memory;
import game.Movement;
import game.Player;
import game.TwoMovements;
import interactivePlayer.InteractivePlayer;

/**
 * A normal game.
 *
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class Game {

    /**
     * To launch from terminal.
     *
     * @param args no args.
     */
    public static void main(String[] args) {
        Chessboard chessboard = Chessboard.createChessboard();
        Player player1 = new InteractivePlayer(Side.WHITE);
        Player player2 = new InteractivePlayer(Side.BLACK);
        Memory memory = new Memory();
        while (true) {
            TwoMovements twoMovements = new TwoMovements();
            memory.add(twoMovements);

            Movement m1 = player1.getMovement(chessboard, memory);
            twoMovements.setWhites(m1);
            chessboard.movePiece(m1);
            if (!chessboard.thereAreTwoKings()) {
                JOptionPane.showMessageDialog(null,
                        memory.toString() + "\n " + Side.WHITE+" WINS");
                return;
            }

            Movement m2 = player2.getMovement(chessboard, memory);
            twoMovements.setBlacks(m2);
            chessboard.movePiece(m2);
            if (!chessboard.thereAreTwoKings()) {
                JOptionPane.showMessageDialog(null,
                        memory.toString() + "\n " + Side.BLACK+" WINS");
                return;
            }
        }
    }

}
