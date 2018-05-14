import javax.swing.*;
import java.awt.*;

public class ObstacleView extends ElementView{
    public ObstacleView(){
        image = "obstacle.jpg";
    }
    /**
     * Visszaadja az akadály képét tartalmazó panelt
     *
     * @return Az akadály képét tartalmazó panel
     */
    public JPanel draw( ){

        return new TilePanel(this);
    }

    /**
     * Visszaadja az akadályhoz és a tartalmazott visitorhoz tartozó képeket tartalmazó panelt
     *
     * @param visitorView A tartalmazott visitorhoz tartozó megjelenítésért felelős objektum
     * @return Az akadályhoz és a tartalmazott visitorhoz tartozó képeket tartalmazó panel
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
