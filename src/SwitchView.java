import javax.swing.*;
import java.awt.*;

public class SwitchView extends ElementView{
    public SwitchView(){
        image = "switch_off.jpg";
    }

    /**
     * Visszaadja a kapcsoló képét tartalmazó panelt
     *
     * @return A kapcsoló képét tartalmazó panel
     */
    public JPanel draw( ){
        return new TilePanel(this);
    }

    /**
     * Visszaadja a kapcsoló és a tartalmazott visitorhoz tartozó képeket tartalmazó panelt
     *
     * @param visitorView A tartalmazott visitorhoz tartozó megjelenítésért felelős objektum
     * @return A kapcsolóhoz és a tartalmazott visitorhoz tartozó képeket tartalmazó panel
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
