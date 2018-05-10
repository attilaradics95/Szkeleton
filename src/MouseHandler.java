import javax.swing.*;
import java.awt.event.MouseEvent;

public class MouseHandler {

    private Game game = Game.getInstance();
    private GameWindow gameWindow = new GameWindow(game);


    //megkapja a GamePanel-től a tiles tömböt
    public void Click(MouseEvent e) {
        //kattintas x koordinataja
        int x = e.getX();
        //kattintas y koordinataja
        int y = e.getY();

        //x, y koordinata atkonvertalasa a megfelelo mezore
        int fieldX = x/60 + 1;
        int fieldY = y/60 + 1;

        gameWindow.HandleClick(fieldX, fieldY);
    }
}
