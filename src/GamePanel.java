import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GamePanel extends JPanel {

    //region Konstruktor
    public GamePanel(ATile[][] tiles, Visitor[][] visitors){

        int numberOfRows = tiles.length;
        int numberOfColumns = tiles[0].length;


        //region Ablak felépítése

        this.setLayout(new GridLayout(numberOfRows,numberOfColumns,0,-5));
        for(int row = 0; row < numberOfRows; row++){
            for(int col = 0; col< numberOfColumns; col++){
                ElementView view = tiles[row][col].getView();
                this.add(view.draw());
            }
        }


        //endregion

    }
    //endregion
}
