package pieces;

import java.util.HashSet;
import java.util.Set;

import game.Chessboard;
import game.Position;

/**
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class Queen extends Piece {

    /**
     * Constructor.
     *
     * @param side whites o blacks.
     */
    public Queen(Side side) {
        super("Q", side);
        setImage("queen_white.png", "queen_black.png");
    }

    /**
     * Calculates the list of piece possible movements.
     *
     * @param position beginnig.
     * @param chessboard  current game status.
     * @return movements chosen.
     */
    public Set<Position> getDestinies(Position position, Chessboard chessboard) {
        Set<Position> destinies = new HashSet<Position>();
        int c = position.getColumn();
        int r = position.getRow();
        desplaza(destinies, c, r, 0, 1, chessboard);
        desplaza(destinies, c, r, 0, -1, chessboard);
        desplaza(destinies, c, r, 1, 0, chessboard);
        desplaza(destinies, c, r, -1, 0, chessboard);
        desplaza(destinies, c, r, 1, 1, chessboard);
        desplaza(destinies, c, r, 1, -1, chessboard);
        desplaza(destinies, c, r, -1, 1, chessboard);
        desplaza(destinies, c, r, -1, -1, chessboard);
        return destinies;
    }

    /**
     * Tries to move the piece through the chess board.
     * If there are no more chess board cells or there is another piece, 
     * it captures or does nothing depending n the side it belongs to. 
     *
     * @param destinies points to the possible destinies.
     * @param c        starting column.
     * @param f        starting row.
     * @param dc       movement in the X axis: columns.
     * @param df       movement in the Y axis: rows.
     * @param chessboard  chessboard of the game.
     */
    private void desplaza(Set<Position> destinies, int c, int r, int mc, int mr, Chessboard chessboard) {
        for (int nc = c + mc, nr = r + mr;
             nc >= 0 && nc < 8 && nr >= 0 && nr < 8;
             nc += mc, nr += mr) {
            if (check(destinies, nc, nr, chessboard)) {
                break;
            }
        }
    }

    /**
     * Try to move to a position
     * if the movement is possible, it is added to destinies. If not, pass.
     *
     * @param destinies contains possible destinies.
     * @param c        column to move.
     * @param f        \row to move.
     * @param chessboard  chessboard of game.
     * @return TRUE if there are no more possible point to move.
     * 	 because of a capture or a piece of the same side.,
     *	FALSE if there is possible to continue moving in this direction.
     */
    private boolean check(Set<Position> destinies, int c, int r, Chessboard chessboard) {
        Position position = new Position(c, r);
        Piece piece = chessboard.getPiece(position);
        if (piece == null) {
            destinies.add(position);
        } else {
            if (piece.getSide() != getSide()) {
                destinies.add(position);
            }
            return true;
        }
        return false;
    }
}
