import javax.swing.*;
import java.awt.*;

public class TargetView extends ElementView {
    public TargetView(){
        image = "target.jpg";
    }

    public JPanel draw( ){
        /*
        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
        */
        return new TilePanel(this);
    }

    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
