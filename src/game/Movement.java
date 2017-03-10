package game;

import pieces.Pawn;
import pieces.Piece;

/**
 * A movement of white or black side
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class Movement {
    /**
     * The piece that is gonna move.
     */
    private final Piece piece;
    /**
     * Position where the piece was.
     */
    private final Position beginning;
    /**
     * New position of the piece.
     */
    private final Position destiny;
    /**
     * If the movement is a capture, it contains the piece object of the capture. 
     * If not, NULL.
     */
    private Piece capture;
    /**
     * True if the movement is a short Castling.
     */
    private boolean shortCastling;
    /**
     * True if the movement is a large Castling.
     */
    private boolean largeCastling;
    /**
     * True if the movement is a check.
     */
    private boolean check;
    /**
     * True if the movement is a check mate.
     */
    private boolean checkMate;

    /**
     * Builder.
     *
     * @param piece   piece to move.
     * @param beginning  position where the piece was before the movement.
     * @param destiny position to move the piece.
     */
    public Movement(Piece piece, Position beginning, Position destiny) {
        this.piece = piece;
        this.beginning = beginning;
        this.destiny = destiny;
        capture = null;
        shortCastling = false;
        largeCastling = false;
        check = false;
        checkMate = false;
    }

    /**
     * Getter.
     *
     * @return position where the piece was.
     */
    public Position getBeginning() {
        return beginning;
    }

    /**
     * Getter.
     *
     * @return position where the piece in gonna be.
     */
    public Position getDestiny() {
        return destiny;
    }

    /**
     * Getter.
     *
     * @return Si se capture una piece, la piece captureda.
     *         Si no se capture ninguna piece, NULL.
     */
    public Piece getCapture() {
        return capture;
    }

    /**
     * Setter.
     *
     * @param capture the piece to be captured, or null if there is not capture.
     */
    public void setCapture(Piece capture) {
        this.capture = capture;
    }

    /**
     * Getter.
     *
     * @return TRUE if there is a short Castling
     */
    public boolean isShortCastling() {
        return shortCastling;
    }

    /**
     * Setter.
     *
     * @param shortCastling TRUE if is a short Castling.
     */
    public void setShortCastling(boolean shortCastling) {
        this.shortCastling = shortCastling;
    }

    /**
     * Getter.
     *
     * @return TRUE if there is a large Castling.
     */
    public boolean isLargeCastling() {
        return largeCastling;
    }

    /**
     * Setter.
     *
     * @param largeCastling TRUE if is a large Castling.
     */
    public void setLargeCastling(boolean largeCastling) {
        this.largeCastling = largeCastling;
    }

    /**
     * Getter.
     *
     * @return TRUE if there is a check.
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * Setter.
     *
     * @param check TRUE if is a check.
     */
    public void setCheck(boolean check) {
        this.check = check;
    }

    /**
     * Getter.
     *
     * @return TRUE if there is a check mate.
     */
    public boolean isCheckMate() {
        return checkMate;
    }

    /**
     * Setter.
     *
     * @param checkMate TRUE if is a check mate.
     */
    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    /**
     * Generates a representation.
     *
     * @return international representation of the movement
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (shortCastling) {
            builder.append("0-0");
        } else if (largeCastling) {
            builder.append("0-0-0");
        } else {
            if (piece.getClass() != Pawn.class) {
                builder.append(piece.getCode());
            }
            if (capture != null) {
                builder.append(" x ");
            }
            builder.append(getDestiny().toString());
        }
        if (check) {
            builder.append(" +");
        }
        if (checkMate) {
            builder.append(" #");
        }
        return builder.toString();
    }
}
