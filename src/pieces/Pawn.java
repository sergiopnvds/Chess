package pieces;

import java.util.HashSet;
import java.util.Set;

import game.Chessboard;
import game.Position;

/**
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class Pawn extends Piece {

    /**
     * Builder.
     *
     * @param side white or black.
     */
    public Pawn(Side side) {
        super("P", side);
        setImage("pawn_white.png", "pawn_black.png");
    }

    /**
     * Calculates the list of piece possible movements.
     *
     * @param position beginnig.
     * @param chessboard  current game status.
     * @return movements chosen.
     */
    public Set<Position> getDestinies(Position position, Chessboard chessboard) {
        if (getSide() == Side.WHITE) {
            return destiniesWhite(position.getColumn(), position.getRow(), chessboard);
        } else {
            return destiniesBlack(position.getColumn(), position.getRow(), chessboard);
        }
    }

    /**
     * Calculate destinies if pawn is white
     *
     * @param c       starting column.
     * @param f       starting row.
     * @param chessboard chess board of the game.
     * @return movements possible.
     */
    private Set<Position> destiniesWhite(int c, int r, Chessboard chessboard) {
        Set<Position> destinies = new HashSet<Position>();
        if (r < 7) {
            Position position = new Position(c, r + 1);
            if (chessboard.getPiece(position) == null) {
                destinies.add(position);
            }
        }
        if (r < 7 && c > 0) {
            Position position = new Position(c - 1, r + 1);
            Piece piece = chessboard.getPiece(position);
            if (piece != null && piece.getSide() != getSide()) {
                destinies.add(position);
            }
        }
        if (r < 7 && c < 7) {
            Position position = new Position(c + 1, r + 1);
            Piece piece = chessboard.getPiece(position);
            if (piece != null && piece.getSide() != getSide()) {
                destinies.add(position);
            }
        }
        if (r == 1) {
            Position position = new Position(c, r + 2);
            if (chessboard.getPiece(position) == null) {
                destinies.add(position);
            }
        }
        return destinies;
    }

    /**
     * Calculate destinies if pawn is black.
     *
     * @param c       starting column.
     * @param f       starting row.
     * @param chessboard chess board of the game.
     * @return movements possible.
     */
    private Set<Position> destiniesBlack(int c, int r, Chessboard chessboard) {
        Set<Position> destinies = new HashSet<Position>();
        if (r > 0) {
            Position position = new Position(c, r - 1);
            if (chessboard.getPiece(position) == null) {
                destinies.add(position);
            }
        }
        if (r > 0 && c > 0) {
            Position position = new Position(c - 1, r - 1);
            Piece piece = chessboard.getPiece(position);
            if (piece != null && piece.getSide() != getSide()) {
                destinies.add(position);
            }
        }
        if (r > 0 && c < 7) {
            Position position = new Position(c + 1, r - 1);
            Piece piece = chessboard.getPiece(position);
            if (piece != null && piece.getSide() != getSide()) {
                destinies.add(position);
            }
        }
        if (r == 6) {
            Position position = new Position(c, r - 2);
            if (chessboard.getPiece(position) == null) {
                destinies.add(position);
            }
        }
        return destinies;
    }
}
