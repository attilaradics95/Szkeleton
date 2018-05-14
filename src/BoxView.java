import javax.swing.*;
import java.awt.*;

public class BoxView  extends ElementView {
    public BoxView(){
        this.image = "box.png";
    }

    /**
     * Visszaadja azt a panelt, amely a doboz képét tartalmazza
     * @return A panel, ami tartalmazza doboz képét
     */
    public JPanel draw( ){
        //a box képét hozzáadjuk a label-höz, majd ezt a panelhoz
        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
    }

    /**
     * Nem használjuk semmire. Azért van megvalósítva, mert muszáj definiálni az ősosztály absztrakt függvényeit.
     *
     * @param visitorView
     * @return
     */
    public JPanel draw(ElementView visitorView){
        return new JPanel();
        // semmi értelme csak muszáj megvalósítani
        // Erre a mezőknél van szükség, hogy lehessen rá visitort rakni a view-ban.
    }


}
