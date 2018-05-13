import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    Controller controller = Controller.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode())
        {
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
                controller.moveWorker(Directions.EAST);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                System.out.println("jobb");
                controller.moveWorker(Directions.SOUTH);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
