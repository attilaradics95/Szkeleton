import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private JPanel mainpanel;

    public GameWindow() {
        super("Killer Sokoban");
        this.setSize(new Dimension(800, 500));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainpanel = new MenuPanel();
        this.add(mainpanel);
    }

    public void setMainpanel(JPanel panel){
        remove(mainpanel);
        this.mainpanel = panel;
        add(mainpanel);
        repaint();
        revalidate();
    }
}
