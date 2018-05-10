import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class GameWindow extends JFrame {
    private JPanel mainpanel;
    Game game;

    public GameWindow(Game game) {

        super("Game");
        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.game = game;

        mainpanel = new MenuPanel(game);
        this.add(mainpanel);

    }

    public void setMainpanel(JPanel panel){
        remove(mainpanel);
        this.mainpanel = panel;
        add(mainpanel);
        repaint();
        revalidate();
        this.pack();
    }
}
