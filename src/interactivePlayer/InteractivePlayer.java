package interactivePlayer;

import game.Player;
import game.Memory;
import game.Movement;
import game.Chessboard;
import pieces.Side;

import javax.swing.*;
import java.awt.*;

/**
 * A player GUI
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class InteractivePlayer implements Player {
    private static final String VERSION = "1.30.2011";

    /**
     * Panel window.
     */
    private final JPanel gui;
    /**
     * Area of chess board.
     */
    private final PiecesPanel piecesPanel;
    /**
     * Area of movements.
     */
    private final JTextArea panelMovements;

    /**
     * Window header
     */
    private final String TITLE;
    /**
     * A copy of the chess board to try experiments before to do the final movement.
     */
    private Chessboard miChessboard;

    /**
     * Builder.
     *
     * @param side whites o blacks.
     */
    public InteractivePlayer(Side side) {
        TITLE = String.format("Chess (%s) %s", VERSION, side + " MOVES");
        miChessboard = new Chessboard();

        gui = new JPanel(new BorderLayout());

        piecesPanel = new PiecesPanel(miChessboard, side);
        gui.add(piecesPanel, BorderLayout.NORTH);

        panelMovements = new JTextArea(10, 10);
        gui.add(new JScrollPane(panelMovements), BorderLayout.CENTER);
    }

    /**
     * Player does movemet.
     *
     * @param chessboard current game status.
     * @param memory historical of pair of movements.
     * @return movement chosen.
     */
    public Movement getMovement(Chessboard chessboard, Memory memory) {
    	miChessboard.copy(chessboard);
        if (memory != null)
            panelMovements.setText(memory.toString());
        Movement movement;
        do {
            int accion = JOptionPane.showConfirmDialog(null,
                    gui, TITLE,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (accion != JOptionPane.OK_OPTION) {
                System.exit(0);
            }
            movement = piecesPanel.getMovement();
        } while (movement == null);
        return movement;
    }
}
