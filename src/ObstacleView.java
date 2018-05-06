import javax.swing.*;

public class ObstacleView {
    private static String image = "obstacle.jpg";
    public static JPanel draw(){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + "obstacle.jpg"),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }
}
