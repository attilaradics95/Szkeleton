import javax.swing.*;
import java.awt.*;

public class TargetView extends ElementView {
    public TargetView(){
        image = "target.jpg";
    }

    /**
     * Üres Target kirajzolása
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
     * A látogatóval terhelt Target kirajzolása.
     * @param visitorView a látogató nézete
     * @return az új nézet
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
