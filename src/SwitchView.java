import javax.swing.*;
import java.awt.*;

public class SwitchView  extends ElementView{
    private  String image = "switch.jpg";
    public JPanel draw( ){
/*
        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;
        */
return new TilePanel(this);
    }
    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }

    public String getImage(){
        return this.image;
    }
}
