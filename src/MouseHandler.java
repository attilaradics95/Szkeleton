import javax.swing.*;
import java.awt.event.MouseEvent;

public class MouseHandler {

    private GameWindow gameWindow;


    //megkapja a GamePanel-től a tiles tömböt
    public void Click(MouseEvent e, ATile[][] tiles) {
        //kattintas x koordinataja
        int x = e.getX();
        //kattintas y koordinataja
        int y = e.getY();

        //x, y koordinata atkonvertalasa a megfelelo mezore
        int fieldX = x/60 + 1;
        int fieldY = y/60 + 1;


        if(tiles[fieldX][fieldY].getVisitor() != null) {
            JOptionPane.showMessageDialog(null, "Ez egy munkás!");
        } else {
            JOptionPane.showMessageDialog(null, "Ez nem munkás!");
        }
        //JOptionPane.showMessageDialog(null, "Ez egy munkás!");
    }
}
