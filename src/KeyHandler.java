import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class KeyHandler {

    GameWindow gameWindow;
    Controller controller = Controller.getInstance();
    Game game = Game.getInstance();

    public KeyHandler(){
        gameWindow = Game.getWindow();
    }



    //billentyű lenyomások kezelése
    //w, a, s, d ill. nyilak - a megfelelő irányba léptetik a munkást
    //m, o - olaj és méz lerakása a tile-ra, amin a munkás áll
    //esc - meghívja az endRound fv-t - ez kiírja dialógusablakba a pontszámot,
    //majd ennek leokézása után kilép
    public void Control(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                controller.moveWorker(Directions.NORTH);
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                controller.moveWorker(Directions.WEST);
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                controller.moveWorker(Directions.SOUTH);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                controller.moveWorker(Directions.EAST);
                break;

            case KeyEvent.VK_M:
                controller.getSelectedworker().putHoney();
                break;

            case KeyEvent.VK_O:
                controller.getSelectedworker().putOil();
                break;

            case KeyEvent.VK_ESCAPE:
                game.endRound();
                break;
        }

    }
}
