import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GamePanel extends JPanel {

    //KONSTRUKTOR
    public GamePanel(String map){

        //region Beolvasás fájlból

        int numberOfRows = 0;
        int numberOfColumns = 0;

        String path = System.getProperty("user.dir");
        File file = new File(path + "/maps/" + map);
        FileInputStream fis1 = null;
        try {
            fis1 = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
        try {
            String line;
            char[] chs = null;
            while ((line = br1.readLine()) != null){
                numberOfRows++;
                chs = line.toCharArray();
            }
            for (char c : chs) {
                if(c == '.' || c == '+' || c == 'X' || c == 'H' || c == 'M' || c == 'O' || c == 'S' || c == 'T'){
                    numberOfColumns++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ATile[][] tiles = new ATile[numberOfRows][numberOfColumns];
        Visitor[][] visitors = new Visitor[numberOfRows][numberOfColumns];
        //endregion

        //region Ablak felépítése

        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/img/" + "worker.jpg");
        this.setLayout(new GridLayout(numberOfRows,numberOfColumns,0,0));
        for(int row = 0; row < numberOfRows; row++){
            for(int col = 0; col< numberOfColumns; col++){
                this.add(WorkerView.draw());
            }
        }



        //endregion
    }
}
