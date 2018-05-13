import javax.swing.*;
import java.awt.*;

public class BoxView  extends ElementView {
    public BoxView(){
        this.image = "box.png";
    }

    public JPanel draw( ){
        //a box képét hozzáadjuk a label-höz, majd ezt a panelhoz
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
