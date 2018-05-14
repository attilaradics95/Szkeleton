import javax.swing.*;
import java.awt.*;

public class WorkerView extends ElementView{
    /**
     * Konstruktor
     *
     * @param selected ha igaz, akkor ki van választva, ha nem, akkor nincs, ennek megfelelően állítjuk be a képet
     */
    public WorkerView(boolean selected){
        if(selected)
            this.image = "selectedworker.png";
        else
            this.image = "worker.png";
    }

    /**
     * Visszaadja azt a panelt, amely a munkás képét tartalmazza
     *
     * @return A panel, ami tartalmazza munkás képét
     */
    public JPanel draw( ){
        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
    }

    /**
     * Nem használjuk semmire. Azért van definiálva, mert muszáj definiálni az ősosztály absztrakt függvényeit.
     *
     * @param visitorView
     * @return egy üres panel
     */
    public JPanel draw(ElementView visitorView){
        return new JPanel();
    }

}
