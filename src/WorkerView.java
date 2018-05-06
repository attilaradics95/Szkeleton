import javax.swing.*;

public class WorkerView {
    private static String image = "worker.jpg";
    public static JPanel draw(){

        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + "worker.jpg"),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }
}
