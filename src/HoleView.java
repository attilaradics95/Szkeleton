import javax.swing.*;

public class HoleView {
    private static String image = "hole.jpg";
    public static JPanel draw(){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + "hole.jpg"),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }
}
