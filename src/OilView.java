import javax.swing.*;
import java.awt.*;

public class OilView extends ElementView {
    /**
     * Konstruktor
     *
     */
    public OilView(){
        image = "oil.jpg";
    }

    /**
     * Visszaadja az olajos mező képét tartalmazó panelt
     *
     * @return Az olajos mező képét tartalmazó panel
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
     * Visszaadja az olajos mező és a tartalmazott visitorhoz tartozó képeket tartalmazó panelt
     *
     * @param visitorView A tartalmazott visitorhoz tartozó megjelenítésért felelős objektum
     * @return Az olajos mezőhöz és a tartalmazott visitorhoz tartozó képeket tartalmazó panel
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
