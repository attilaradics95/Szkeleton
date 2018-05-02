import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    //private GamePanel gameView;
    private MenuPanel startMenu;

    public GameWindow() {
        super("Killer Sokoban");
        this.setSize(new Dimension(800, 500));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        startMenu = new MenuPanel();

    }
}
