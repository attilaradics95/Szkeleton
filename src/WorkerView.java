import javax.swing.*;
import java.awt.*;

public class WorkerView extends ElementView{
    //ha igaz, akkor ki van választva, ha nem, akkor nincs
    //ennek megfelelően állítjuk be a képet
    public WorkerView(boolean selected){
        if(selected)
            this.image = "selectedworker.png";
        else
            this.image = "worker.png";
    }

    public JPanel draw( ){
        //a képét hozzáadjuk a label-höz, majd ezt a panelhoz
        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
    }

    public JPanel draw(ElementView visitorView){
        return new JPanel();
        //semmi értelme csak muszáj megvalósítani
        //erre a mezőknél van szükség,
        // hogy lehessen rá visitort rakni a view-ban
    }

}
