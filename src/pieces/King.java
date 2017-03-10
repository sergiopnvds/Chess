package pieces;

import java.util.HashSet;
import java.util.Set;

import game.Chessboard;
import game.Position;

/**
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class King
        extends Piece {

    /**
     * Builder.
     *
     * @param side white or black.
     */
    public King(Side side) {
        super("K", side);
        setImage("king_white.png", "king_black.png");
    }

    /**
     * Calculates the list of piece possible movements.
     *
     * @param position beginnig.
     * @param chessboard  current game status.
     * @return movements chosen.
     */
    public Set<Position> getDestinies(Position position, Chessboard chessboard) {
        Set<Position> positiones = new HashSet<Position>();
        int c = position.getColumn();
        int r = position.getRow();
        int ca = Math.max(0, c - 1);
        int cz = Math.min(7, c + 1);
        int ra = Math.max(0, r - 1);
        int rz = Math.min(7, r + 1);
        for (int nc = ca; nc <= cz; nc++) {
            for (int nr = ra; nr <= rz; nr++) {
                Position destino = new Position(nc, nr);
                Piece piece = chessboard.getPiece(destino);
                if (piece == null || piece.getSide() != getSide()) {
                    positiones.add(destino);
                }
            }
        }
        return positiones;
    }
}
