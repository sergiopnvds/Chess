package pieces;

import javax.swing.*;

import game.Chessboard;
import game.Position;

import java.awt.*;
import java.net.URL;
import java.util.Set;

/**
 * A pieze of the chess game
 * 
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public abstract class Piece {
    private final String code;
    private final Side side;
    private Image image;

    /**
     * Builder.
     *
     * @param code notation.
     * @param side  whites o blacks.
     */
    protected Piece(String code, Side side) {
        this.code = code;
        this.side = side;
    }

    /**
     * Getter.
     *
     * @return code to print notation.
     */
    public final String getCode() {
        return code;
    }

    /**
     * Getter.
     *
     * @return whites o blacks.
     */
    public final Side getSide() {
        return side;
    }

    /**
     * Loads an image to draw in the chess board.
     *
     * @param white image to white pieces.
     * @param black  image to black pieces.
     */
    void setImage(String white, String black) {
        URL url;
        if (side == Side.WHITE) {
            url = getClass().getResource(white);
        } else {
            url = getClass().getResource(black);
        }
        ImageIcon icon = new ImageIcon(url);
        image = icon.getImage();
    }

    /**
     * Getter.
     *
     * @return image to draw.
     */
    public final Image getImage() {
        return image;
    }

    /**
     * Calculate a list with possible movements of a piece.
     *
     * @param position beginning point.
     * @param chessboard  game state.
     * @return movements possible.
     */
    public abstract Set<Position> getDestinies(Position position, Chessboard chessboard);
}
