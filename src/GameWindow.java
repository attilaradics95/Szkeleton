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

    /**
     * Kicseréli a panelt, amit tartalmaz az ablak
     *
     * @param panel A panel, amire ki szeretnénk cserélni a mostanit.
     */
    public void setMainpanel(JPanel panel){
        remove(mainpanel);
        this.mainpanel = panel;
        add(mainpanel);
        this.pack();
        repaint();
        revalidate();
    }

    /**
     * Kiválasztja azt a munkást, ami az adott koordinátájú mezőben található.
     *
     * @param x A kiválasztott mező x koordináátja
     * @param y A kiválasztott mező y koordinátája
     */
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
