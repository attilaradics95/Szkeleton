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
        //ablak beállításai
        super("Game");
        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.game = game;

        //először a menü jelenik meg
        mainpanel = new MenuPanel(game);
        this.add(mainpanel);

    }

    //panel cseréje
    public void setMainpanel(JPanel panel){
        remove(mainpanel);
        this.mainpanel = panel;
        add(mainpanel);
        this.pack();
        repaint();
        revalidate();
    }

    //pályára kattintás kezelése
    public void HandleClick(int x, int y) {
        ATile[][] tiles = game.getMap();
        Controller controller = Controller.getInstance();
        //fordítva kell a két koordináta
        if(tiles[y][x].getVisitor() != null) {
            controller.selectWorker(tiles[y][x].getVisitor().getId());
            JOptionPane.showMessageDialog(null, "munkás id: " + tiles[y][x].getVisitor().getId());
        } else {
            JOptionPane.showMessageDialog(null, "Ez nem munkás!");
        }
    }
}
