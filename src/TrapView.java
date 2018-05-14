import javax.swing.*;
import java.awt.*;

public class TrapView  extends ElementView{
    public TrapView(){
        image = "tile.jpg";
    }

    /**
     * A Trap osztály megjelenítéséért felel.
     * @return az új panelt adja visza
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

    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
