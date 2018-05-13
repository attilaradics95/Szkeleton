import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyListener implements KeyListener {
    Controller controller;

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("KeyTyped");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KeyPressed");

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                System.out.println("fel");
                //controller.moveWorker(Directions.NORTH);
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:

                //controller.moveWorker(Directions.WEST);
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:

                //controller.moveWorker(Directions.EAST);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:

                //controller.moveWorker(Directions.SOUTH);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("KeyReleased");

    }

}