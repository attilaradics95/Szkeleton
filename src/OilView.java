import javax.swing.*;
import java.awt.*;

public class OilView extends ElementView {
    public OilView(){
        image = "oil.jpg";
    }

    /**
     * Az üres olajos mező kirajzolása
     * @return az új nézet
     */
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

    /**
     * látogatót tartalmazó olajos mező kirajzolása
     * @param visitorView a látogató nézete
     * @return a közös nézet
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
