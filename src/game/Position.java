package game;

/**
 * A position in the chess board.
 * Move form array [0][0] to [7][7],
 * to international notation of 'a1' to 'h8'
 * 
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class Position {
    /**
     * Column.
     * Coding the range 0 .. 7,corresponding to  'a' .. 'h'.
     * From rigth to left.
     */
    private final int column;
    /**
     * Row.
     * Coding the range 0 .. 7,corresponding to  1 .. 8.
     * From down to up
     */
    private final int row;

    /**
     * Builder.
     *
     * @param column range 0-7.
     * @param row    range 0-7.
     * @throws IllegalArgumentException if column or row are not in the chessboard.
     */
    public Position(int column, int row)
            throws IllegalArgumentException {
        if (column < 0 || column > 7) {
            throw new IllegalArgumentException("Wrong column: " + column);
        }
        if (row < 0 || row > 7) {
            throw new IllegalArgumentException("Wrong row: " + row);
        }
        this.column = column;
        this.row = row;
    }

    /**
     * Bulder.
     *
     * @param ni international notation: 'a'-'h' + '1'-'8'.
     * @throws IllegalArgumentException wrong position.
     */
    public Position(String ni)
            throws IllegalArgumentException {
        if (ni == null || ni.length() != 2) {
            throw new IllegalArgumentException("Wrong position: " + ni);
        }
        char ci = ni.charAt(0);
        if (ci < 'a' || ci > 'h') {
            throw new IllegalArgumentException("Wrong position: " + ni);
        }
        char fi = ni.charAt(1);
        if (fi < '1' || fi > '8') {
            throw new IllegalArgumentException("Wrong position: " + ni);
        }
        this.column = ci - 'a';
        this.row = fi - '1';
    }

    /**
     * Getter.
     *
     * @return column in range 0-7 corresponding in international notation with 'a'-'h'.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Getter.
     *
     * @return row in range 0-7 corresponding in international notation with '1'-'8'.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter.
     *
     * @return international notation.
     */
    public String toString() {
        char c = (char) ('a' + column);
        int f = 1 + row;
        return String.format("%c%d", c, f);
    }

    /**
     * Checks if a position is equals to other.
     *
     * @param o other object.
     * @return true if they are the same position.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        if (column != position.column) {
            return false;
        }
        if (row != position.row) {
            return false;
        }
        return true;
    }

    /**
     * Getter.
     *
     * @return valor arbitrary value that is the same for the identical positions.
     */
    public int hashCode() {
        return column * 31 + row;
    }
}
