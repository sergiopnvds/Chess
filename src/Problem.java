import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

import javax.swing.JOptionPane;

import game.Chessboard;
import game.TwoMovements;
import interactivePlayer.InteractivePlayer;
import game.Player;
import game.Memory;
import game.Movement;
import game.Position;
import pieces.Side;
import pieces.King;
import pieces.Piece;

/**
 * Can launch game saved from a file.
 *
 * @author Sergio Penavades 
 * @version 01/30/2011
 */
public class Problem extends java.lang.Object{
    
    String chain;




    private void cargar(Side side, String chain, Chessboard chessboard) throws java.lang.IllegalArgumentException{
          
            String[] trozo = chain.split(",");
            int i = 0;
            Piece piece= null;
            Position position;
            String que = trozo[i].trim().substring(0, 1);
            String donde = trozo[i].trim().substring(1);
            if (que.equals("K")) piece=new King(side);
            position = new Position(donde);
            chessboard.setPiece(position, piece);
            //Piece piece ;
    }


    /**
     * To launch from terminal.
     *
     * @param args not iin use.
     */
    public static void main(String[] args) throws java.io.IOException, java.lang.IllegalArgumentException {


    Chessboard chessboard = new Chessboard ();
            Reader reader= new FileReader(args[0]);
            Properties properties = new Properties();
            properties.load(reader);

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
                    memory.toString() + "\n " + Side.WHITE +" WINS");
                    return;
                }

                Movement m2 = player2.getMovement(chessboard, memory);
                twoMovements.setBlacks(m2);
                chessboard.movePiece(m2);
                if (!chessboard.thereAreTwoKings()) {
                    JOptionPane.showMessageDialog(null, memory.toString() + "\n " + Side.BLACK+" WINS");
                    return;
                }
            }
        }


    }