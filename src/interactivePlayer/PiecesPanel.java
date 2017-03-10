package interactivePlayer;

import pieces.Side;
import pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.Chessboard;
import game.Movement;
import game.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

/**
 * Panel with the chess board and all pieces.
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class PiecesPanel extends JPanel {
    private static final int MARGIN = 30;
    private static final int CELL = 60;
    private static final int CHESSBOARD = 8 * CELL;

    private static final int WIDTH = (MARGIN + CHESSBOARD + MARGIN);
    private static final int HEIGHT = WIDTH;
    // coordenadas de extremo superior izquierdo (NorthWest)
    private int nwx = MARGIN;
    private int nwy = MARGIN;

    private static final java.awt.Color GREY1 = new java.awt.Color(0xF0F0F0);
    private static final java.awt.Color GREY3 = new java.awt.Color(0xB0B0B0);

    private Font myFont;

    private final Chessboard chessboard;
    private final Side side;
    private int cursorX;
    private int cursorY;
    private MyMouseListener mouseListener;
    private Image board;
    private Image image;
    
    

    /**
     * Builder.
     *
     * @param chessboard chessboard to obtain pieces locations.
     * @param side   to know what side moves nows.
     */
    public PiecesPanel(Chessboard chessboard, Side side) {
    	FlowLayout flowLayout = (FlowLayout) getLayout();
    	flowLayout.setAlignOnBaseline(true);
        this.chessboard = chessboard;
        this.side = side;
        cursorX = 4;
        cursorY = side == Side.WHITE ? 6 : 1;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocusInWindow();
        mouseListener = new MyMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    
    /**
     * Loads a chess board image.
     *s
     */
    
    void setImage() {
        URL url = getClass().getResource("Chessboard.png"); 
        ImageIcon icon = new ImageIcon(url);
        board = icon.getImage();
    	 
    }

    /**
     * Getter.
     *
     * @return movement chosen.If it is not chosen, returns NULL.
     */
    public Movement getMovement() {
        return mouseListener.getMovement();
    }

    /**
     * Draw the game.
     *
     * @param g 2D system to draw.
     */
    public void paint(Graphics g) {
        update(g);
    }

    /**
     * Update window.
     *
     * @param g g 2D system to draw.
     */
    public void update(Graphics g) {
        if (myFont == null) {
            myFont = g.getFont();
            myFont = new Font(myFont.getName(), Font.PLAIN, 20);
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               drawCell(g, i, j);
            }
        }
        //g.setFont(myFont);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            drawRow(g, i);
        }
        for (int i = 0; i < 8; i++) {
            drawColumn(g, i);
        }
    }

    /**
     * Row label.
     *
     * @param g graphic systems.
     * @param i row number.
     */
    private void drawRow(Graphics g, int i) {
        String s = String.valueOf(8 - i);
        g.drawString(s, 12, MARGIN + i * CELL + 35);
        g.drawString(s, WIDTH - 20, MARGIN + i * CELL + 35);
    }

    /**
     * Culumn label.
     *
     * @param g graphic systems.
     * @param i row number.
     */
    private void drawColumn(Graphics g, int i) {
        String s = String.valueOf((char) ('a' + i));
        g.drawString(s, MARGIN + i * CELL + 27, 20);
        g.drawString(s, MARGIN + i * CELL + 27, HEIGHT - 10);
    }

    /**
     * Fill a cell.
     *
     * @param g graphic system.
     * @param x column.
     * @param y row.
     */
    private void drawCell(Graphics g, int x, int y) {
        Color color;
        if ((x + y) % 2 == 0) {
            color = GREY1;
        } else {
            color = GREY3;
        }
        if (x == cursorX && y == cursorY) {
            color = Color.GREEN;
        }
       //setImage();
       //g.drawImage(board, 0, 0, getWidth(), getHeight(), null);
        g.setColor(color);
        g.fillRect(nwx + x * CELL + 1, nwy + y * CELL + 1, 59, 59);
        Piece piece = chessboard.getPiece(new Position(x, 7 - y));
        if (piece != null) {
            int vx = nwx + x * CELL;
            int vy = nwy + y * CELL;
            Image image = piece.getImage();
            g.drawImage(image, vx, vy, CELL, CELL, null);
        }
    }

    /**
     * Mouse Listener.
     */
    private class MyMouseListener
            implements MouseListener, MouseMotionListener {
        private Position positionInitial;
        private Position positionCurrent;
        private Piece selected, capture;
        private Set<Position> destinies;

        /**
         * Translates from pixel to row and column.
         *
         * @param e mouse event.
         * @return cell position clicked.
         */
        private Position getPosition(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int px = (x - MARGIN) / CELL;
            int py = 7 - (y - MARGIN) / CELL;
            if (px < 0 || px > 7) {
                return null;
            }
            if (py < 0 || py > 7) {
                return null;
            }
            return new Position(px, py);
        }

        /**
         * Activates with click.
         *
         * @param e mouse event.
         */
        public void mouseClicked(MouseEvent e) {
            requestFocusInWindow();
            positionCurrent = getPosition(e);
            if (positionCurrent == null) {
                return;
            }
            cursorX = positionCurrent.getColumn();
            cursorY = 7 - positionCurrent.getRow();
            repaint();
        }

        /**
         * Activates when mouse pressed.
         *
         * @param e mouse event.
         */
        public void mousePressed(MouseEvent e) {
            if (selected != null) {
                chessboard.setPiece(positionCurrent, null);
                if (capture != null) {
                    chessboard.setPiece(positionCurrent, capture);
                }
                chessboard.setPiece(positionInitial, selected);
            }
            positionInitial = positionCurrent = getPosition(e);
            if (positionCurrent == null) {
                return;
            }
            cursorX = positionCurrent.getColumn();
            cursorY = 7 - positionCurrent.getRow();
            selected = chessboard.getPiece(positionCurrent);
            if (selected == null) {
                return;
            }
            if (selected.getSide() != side) {
                selected = null;
            }
            capture = null;
            if (selected != null) {
                destinies = selected.getDestinies(positionCurrent, chessboard);
            }
            repaint();
        }

        /**
         * Activates when mouse drags.
         *
         * @param e mouse event.
         */
        public void mouseDragged(MouseEvent e) {
            if (selected == null || destinies == null || destinies.size() == 0) {
                return;
            }
            Position newPosition = getPosition(e);
            if (newPosition == null) {
                return;
            }
            if (!newPosition.equals(positionCurrent) && (newPosition.equals(positionInitial) || destinies.contains(newPosition))) {
                chessboard.setPiece(positionCurrent, capture);
                positionCurrent = newPosition;
                capture = chessboard.getPiece(positionCurrent);
                chessboard.setPiece(positionCurrent, selected);
                repaint();
            }
        }

        /**
         * Activates when mouse moves.
         *
         * @param e mouse event.
         */
        public void mouseMoved(MouseEvent e) {
            // no nos afecta.
        }

        /**
         * Activates when mouse is released.
         *
         * @param e mouse event.
         */
        public void mouseReleased(MouseEvent e) {
            // no nos afecta.
        }

        /**
         * Activates when mouse go inside the graphic area.
         *
         * @param e mouse event.
         */
        public void mouseEntered(MouseEvent e) {
            // no nos afecta.
        }

        /**
         * Activates when mouse leaves the graphic area
         *
         * @param e mouse event.
         */
        public void mouseExited(MouseEvent e) {
            // no nos afecta.
        }

        /**
         * Getter.
         *
         * @return movement done with the mouse.
         * If piece is not move, returns null.
         */
        private Movement getMovement() {
            if (selected == null) {
                return null;
            }
            if (positionInitial == null || positionCurrent == null || positionInitial.equals(positionCurrent)) {
                return null;
            }
            Movement movement = new Movement(selected, positionInitial, positionCurrent);
            movement.setCapture(capture);
            selected = null;
            return movement;
        }
    }
}
