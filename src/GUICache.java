import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GUICache {

    //Attribútumok
    BufferedImage tile;
    BufferedImage hole;
    BufferedImage honey;
    BufferedImage obstacle;
    BufferedImage oil;
    BufferedImage inactiveSwitch;
    BufferedImage target;
    BufferedImage trap;

    ImageIcon worker;
    ImageIcon box;

    HashMap<String, BufferedImage> elements = new HashMap<String, BufferedImage>();
    HashMap<String, ImageIcon> visitors = new HashMap<String, ImageIcon>();


    /**
     * Konstruktor
     *
     */
    public GUICache(){

        TileView tileView = new TileView();
        String tileViewImage = tileView.getImage();
        File tileFile = new File(System.getProperty("user.dir") + "/img/" + tileViewImage);
        try {
            this.tile = ImageIO.read(tileFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(tileViewImage,tile);

        HoleView holeView = new HoleView();
        String holeViewImage = holeView.getImage();
        File holeFile = new File(System.getProperty("user.dir") + "/img/" + holeViewImage);
        try {
            this.hole = ImageIO.read(holeFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(holeViewImage,hole);

        HoneyView honeyView = new HoneyView();
        String honeyViewImage = honeyView.getImage();
        File honeyFile = new File(System.getProperty("user.dir") + "/img/" + honeyViewImage);
        try {
            this.honey = ImageIO.read(honeyFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(honeyViewImage,honey);

        ObstacleView obstacleView = new ObstacleView();
        String obstacleViewImage = obstacleView.getImage();
        File obstacleFile = new File(System.getProperty("user.dir") + "/img/" + obstacleViewImage);
        try {
            this.obstacle = ImageIO.read(obstacleFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(obstacleViewImage,obstacle);

        OilView oilView = new OilView();
        String oilViewImage = oilView.getImage();
        File oilFile = new File(System.getProperty("user.dir") + "/img/" + oilViewImage);
        try {
            this.oil = ImageIO.read(oilFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(oilViewImage,oil);

        SwitchView switchView = new SwitchView();
        String switchViewImage = switchView.getImage();
        File switchFile = new File(System.getProperty("user.dir") + "/img/" + switchViewImage);
        try {
            this.inactiveSwitch = ImageIO.read(switchFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(switchViewImage,inactiveSwitch);

        TargetView targetView = new TargetView();
        String targetViewImage = targetView.getImage();
        File targetFile = new File(System.getProperty("user.dir") + "/img/" + targetViewImage);
        try {
            this.target = ImageIO.read(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(targetViewImage,target);

        TrapView trapView = new TrapView();
        String trapViewImage = trapView.getImage();
        File trapFile = new File(System.getProperty("user.dir") + "/img/" + trapViewImage);
        try {
            this.trap = ImageIO.read(trapFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        elements.put(trapViewImage,trap);
    }

    public BufferedImage getBufferedImage(String fileName){
        return elements.get(fileName);
    }

    public ImageIcon getImageIcon(String fileName){
        return visitors.get(fileName);
    }
}
