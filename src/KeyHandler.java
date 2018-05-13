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



    public void Control(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                System.out.println("fel");
                controller.moveWorker(Directions.NORTH);
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                System.out.println("bal");
                controller.moveWorker(Directions.WEST);
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                System.out.println("le");
                controller.moveWorker(Directions.SOUTH);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                System.out.println("jobb");
                controller.moveWorker(Directions.EAST);
                break;

            case KeyEvent.VK_M:
                System.out.println("méz");
                controller.getSelectedworker().putHoney();
                break;

            case KeyEvent.VK_O:
                System.out.println("olaj");
                controller.getSelectedworker().putOil();
                break;

            case KeyEvent.VK_ESCAPE:
                System.out.println("kilepes");
                gameWindow.dispose();
                break;
        }
        if(controller.getNumberOfWorkers()==0 || game.boxes.size() == 0){
            int player = game.getCurrentplayer();
            int points = game.getPoints(player);
            String message = "Vége a körnek!\nSzerzett pontok: " + points;
            JOptionPane.showMessageDialog(null, message);
            gameWindow.dispose();
        }

    }
}
