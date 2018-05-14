import javax.swing.*;
import java.awt.*;

public class HoneyView  extends ElementView{
    /**
     * Konstruktor
     *
     */
    public HoneyView(){
        image = "honey.jpg";
    }

    /**
     * Visszaadja a mézes mező képét tartalmazó panelt
     *
     * @return A mézes mező képét tartalmazó panel
     */
    public JPanel draw( ){
        return new TilePanel(this);
    }

    /**
     * Visszaadja a mézes mezőhoz és a tartalmazott visitorhoz tartozó képeket tartalmazó panelt
     *
     * @param visitorView A tartalmazott visitorhoz tartozó megjelenítésért felelős objektum
     * @return Az entitáshoz és a tartalmazott visitorhoz tartozó képeket tartalmazó panel
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
