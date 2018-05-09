import javax.swing.*;
import java.awt.*;

public class BoxView  extends ElementView {
    private static String image = "worker.jpg";
    public JPanel draw(){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + "box.jpg"),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
    }
}
