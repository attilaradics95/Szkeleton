import javax.swing.*;

public class TrapView {
    private static String image = "trap.jpg";
    public static JPanel draw(){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + "trap.jpg"),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }
}
