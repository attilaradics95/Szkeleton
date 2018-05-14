import javax.swing.*;
import java.awt.*;

public class HoleView  extends ElementView{
    public HoleView(){
        image = "hole.jpg";
    }

    /**
     * Visszaadja a lyuk képét tartalmazó panelt.
     *
     * @return A lyuk képét tartalmazó panel.
     */
    public JPanel draw( ){
        return new TilePanel(this);
    }

    /**
     * Visszaadja a lyuk képét, és a tartalmazott visitor képét tartalmazó panelt.
     * Nem használjuk, mert a lyuk nem tartalmazhat semmit.
     * Muszáj deiniálni, mert az ősosztályban absztrakt függvényként van deklarálva.
     *
     * @return A lyuk képét és a tartalmazott visitor képét tartalmazó panel.
     */
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
