import javax.swing.*;
import java.awt.event.MouseEvent;

public class MouseHandler {

    private GameWindow gameWindow;

    public void Click(MouseEvent e) {
        //kattintas x koordinataja
        int x = e.getX();
        //kattintas y koordinataja
        int y = e.getY();

        //x, y koordinata atkonvertalasa a megfelelo mezore
        int fieldX = x/60 + 1;
        int fieldY = y/60 + 1;

        Worker selectedWorker = new Worker();

        ATile[][] tiles = gameWindow.game.getTiles();
        if(tiles[fieldX][fieldY].getVisitor() != null) {
            JOptionPane.showMessageDialog(null, "Ez egy munkás!");
        } else {
            JOptionPane.showMessageDialog(null, "Ez nem munkás!");
        }
    }
}
