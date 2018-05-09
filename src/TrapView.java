import javax.swing.*;
import java.awt.*;

public class TrapView  extends ElementView{
    private static String image = "tile_hq.jpg";
    public JPanel draw(){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
    }
}
