import javax.swing.*;
import java.awt.*;

public class TrapView  extends ElementView{
    public TrapView(){
        image = "tile.jpg";
    }

    /**
     * Visszaadja az csapda képét tartalmazó panelt
     *
     * @return Az csapda képét tartalmazó panel
     */
    public JPanel draw( ){
        return new TilePanel(this);
    }

    /**
     * Visszaadja a csapda és a tartalmazott visitorhoz tartozó képeket tartalmazó panelt
     *
     * @param visitorView A tartalmazott visitorhoz tartozó megjelenítésért felelős objektum
     * @return A csapdához és a tartalmazott visitorhoz tartozó képeket tartalmazó panel
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
