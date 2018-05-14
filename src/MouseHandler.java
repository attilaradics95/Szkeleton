import javax.swing.*;
import java.awt.event.MouseEvent;

public class MouseHandler {

    private GameWindow gameWindow;

    public MouseHandler(){
        gameWindow = Game.getWindow();
    }


    /**
     * Feldolgozza a kattintást. Továbbhívja a kattintásra reagáló függvényt.
     *
     * @param e Egér esemény
     */
    public void Click(MouseEvent e) {
        //kattintas x koordinataja
        int x = e.getX();
        //kattintas y koordinataja
        int y = e.getY();

        //x, y koordinata atkonvertalasa a megfelelo mezore
        int fieldX = x/60;
        int fieldY = y/60;

        System.out.println(fieldX + " " + fieldY);

        gameWindow.HandleClick(fieldX, fieldY);
    }
}
