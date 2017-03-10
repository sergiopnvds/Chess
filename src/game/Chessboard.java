package game;

import pieces.*;

/**
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class 
Chessboard {
    /**
     * The 64 cells of a chessboard.
     */
    private Piece[][] pieces;

    /**
     * Builder.
     * A chessboard without pieces.
     */
    public Chessboard() {
        this.pieces = new Piece[8][8];
    }

    /**
     * Copy to this chess board the pieces situation of another chess board.
     *
     * @param chessboard other chess board which pieces will be copied.
     */
    public void copy(Chessboard chessboard) {
        for (int col = 0; col < 8; col++) {
            System.arraycopy(chessboard.pieces[col], 0, pieces[col], 0, 8);
        }
    }

    /**
     * Detects if there are two kings in the game to continue playing.
     *
     * @return TRUE if there are two king in the chess board.
     */
    public boolean thereAreTwoKings() {
        int n = 0;
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Piece piece = pieces[col][row];
                if (piece != null && piece.getCode().equals("K")) {
                    n++;
                }
            }
        }
        return n == 2;
    }

    /**
     * To inspect the chess board.
     *
     * @param position position to see.
     * @return piece in the indicated position. If cell is empty, returns NULL
     *     
     */
    public Piece getPiece(Position position) {
        int col = position.getColumn();
        int row = position.getRow();
        return pieces[col][row];
    }

    /**
     * To add pieces to the Chess board.
     * If there is a piece in the position, the function substitutes it (capture)
     *
     * @param position position to allocate the piece.
     * @param piece    piece to put in the Chess board.
     */
    public void setPiece(Position position, Piece piece) {
        int col = position.getColumn();
        int row = position.getRow();
        pieces[col][row] = piece;
    }

    /**
     * Executes a movement.
     * Move the piece form the beginning to the destiny cell.
     *
     * @param movement movement to execute.
     */
    public void movePiece(Movement movement) {
        Position beginning = movement.getBeginning();
        Position destiny = movement.getDestiny();
        setPiece(destiny, getPiece(beginning));
        setPiece(beginning, null);
    }

    /**
     * Creates a Chess board with the 16 pieces in the nitial positoin
     *
     * @return Chess board inialized to start a standard game.
     * @throws IllegalArgumentException wrong position.
     */
    public static Chessboard createChessboard()throws IllegalArgumentException {
        
    	Chessboard chessboard = new Chessboard();       
    	chessboard.setPiece(new Position("a1"), new Rook(Side.WHITE));       
    	chessboard.setPiece(new Position("b1"), new Kinght(Side.WHITE));        
    	chessboard.setPiece(new Position("c1"), new Bishop(Side.WHITE));       
    	chessboard.setPiece(new Position("d1"), new Queen(Side.WHITE));        
    	chessboard.setPiece(new Position("e1"), new King(Side.WHITE));        
    	chessboard.setPiece(new Position("f1"), new Bishop(Side.WHITE));       
    	chessboard.setPiece(new Position("g1"), new Kinght(Side.WHITE));    
    	chessboard.setPiece(new Position("h1"), new Rook(Side.WHITE));        
    	chessboard.setPiece(new Position("a2"), new Pawn(Side.WHITE));        
    	chessboard.setPiece(new Position("b2"), new Pawn(Side.WHITE));        
    	chessboard.setPiece(new Position("c2"), new Pawn(Side.WHITE));
    	chessboard.setPiece(new Position("d2"), new Pawn(Side.WHITE));        
    	chessboard.setPiece(new Position("e2"), new Pawn(Side.WHITE));        
    	chessboard.setPiece(new Position("f2"), new Pawn(Side.WHITE));        
    	chessboard.setPiece(new Position("g2"), new Pawn(Side.WHITE));        
    	chessboard.setPiece(new Position("h2"), new Pawn(Side.WHITE));
    	chessboard.setPiece(new Position("a8"), new Rook(Side.BLACK));
        chessboard.setPiece(new Position("b8"), new Kinght(Side.BLACK));    
    	chessboard.setPiece(new Position("c8"), new Bishop(Side.BLACK));       
    	chessboard.setPiece(new Position("d8"), new Queen(Side.BLACK));        
    	chessboard.setPiece(new Position("e8"), new King(Side.BLACK));       
    	chessboard.setPiece(new Position("f8"), new Bishop(Side.BLACK));        
    	chessboard.setPiece(new Position("g8"), new Kinght(Side.BLACK));        
    	chessboard.setPiece(new Position("h8"), new Rook(Side.BLACK));        
    	chessboard.setPiece(new Position("a7"), new Pawn(Side.BLACK));       
    	chessboard.setPiece(new Position("b7"), new Pawn(Side.BLACK));        
    	chessboard.setPiece(new Position("c7"), new Pawn(Side.BLACK));        
    	chessboard.setPiece(new Position("d7"), new Pawn(Side.BLACK));        
    	chessboard.setPiece(new Position("e7"), new Pawn(Side.BLACK));        
    	chessboard.setPiece(new Position("f7"), new Pawn(Side.BLACK));        
    	chessboard.setPiece(new Position("g7"), new Pawn(Side.BLACK));     
    	chessboard.setPiece(new Position("h7"), new Pawn(Side.BLACK));
        return chessboard;
    }

    /**
     * Text representation of the chess board.
     *
     * @return format use to load games previously started.
     */
    public String toString() {
        StringBuilder whites = new StringBuilder();
        StringBuilder blacks = new StringBuilder();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Piece piece = pieces[col][row];
                if (piece != null && piece.getSide() == Side.WHITE) {
                    if (whites.length() > 0) {
                        whites.append(", ");
                    }
                    whites.append(piece.getCode()).append(new Position(col, row));
                }
                if (piece != null && piece.getSide() == Side.BLACK) {
                    if (blacks.length() > 0) {
                        blacks.append(", ");
                    }
                    blacks.append(piece.getCode()).append(new Position(col, row));
                }
            }
        }
        return String.format("whites= %s%nblacks= %s", whites, blacks);
    }
}
