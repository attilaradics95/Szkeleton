import javax.swing.*;

public class BoxView {
    private static String image = "box.jpg";
    public static JPanel draw(){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + "box.jpg"),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }
}
