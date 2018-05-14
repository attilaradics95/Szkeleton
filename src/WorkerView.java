import javax.swing.*;
import java.awt.*;

public class WorkerView extends ElementView{
    /**
     * @param selected ha igaz, akkor ki van választva, ha nem, akkor nincs, ennek megfelelően állítjuk be a képet
     */
    public WorkerView(boolean selected){
        if(selected)
            this.image = "selectedworker.png";
        else
            this.image = "worker.png";
    }

    /**
     * a képét hozzáadjuk a label-höz, majd ezt a panelhoz
     * @return az új panellel tér vissza
     */
    public JPanel draw( ){
        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
    }

    /**
     * semmi értelme csak muszáj megvalósítani
     * erre a mezősknél van szükség,
     * hogy lehessen rá visitort rakni a view-ban
     * @param visitorView
     * @return
     */
    public JPanel draw(ElementView visitorView){
        return new JPanel();
    }

}
