import javax.swing.*;
import java.awt.*;

public class BoxView  extends ElementView {
    private String image;

    public BoxView(){
        this.image = "box.png";
    }

    public String getImage(){
        return this.image;
    }
    public JPanel draw( ){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
    }

    public JPanel draw(ElementView visitorView){
        return new JPanel();
        //ennek semmi értelme csak muszáj megvalósítani
    }


}
