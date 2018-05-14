import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    //region Singleton attribútumok és metódusok
    /**Singleton metódusok és attributumok*/
    private static Game instance = null;
    private Game(){
        boxes = new ArrayList<>();
        switches = new ArrayList<>();
        traps = new ArrayList<>();
    }
    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }
    //endregion

    //region Attribútumok
    /**Attributumok*/
    private Controller controller;
    private static GameWindow window;
    private boolean roundover = false;
    private ArrayList<Trap> traps;
    public ArrayList<Switch> switches;
    public ArrayList<Box> boxes;
    private ArrayList<Integer> points = new ArrayList<Integer>();
    private int currentplayer = 0;

    //projekt mappa elérési útvonala
    //ezen a mappán belül lesznek a bemeneti pályák
    private static String path;

    //ebben tároljuk el a fájlból beolvasott dolgokat
    private ATile[][] tiles;
    private Visitor[][] visitors;

    //pontok növelése
    public void addPoint(){
        int point = points.get(currentplayer);
        points.set(currentplayer, ++point);
    }

    //pontok kiíratásához lekérdező fv
    public int getPoints(int i){
        return points.get(i);
    }
    //endregion

    //region Metódusok
    /**
     * Visszaadja a jelenlegi játkost
     *
     * @return A jelenlegi játékos.
     */
    public int getCurrentplayer(){
        return currentplayer;
    }

    /**
     * Kicserél egy mezőt
     * @param  old A régi mező, amit kicserélünk
     * @param  newtile Az új mező, amire kicseréljük
    */
    public void swap(ATile old, ATile newtile) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (old == tiles[i][j]) {
                    tiles[i][j] = newtile;
                }
            }
        }
        Visitor v = old.getVisitor();
        newtile.setVisitor(v);
        v.setCurrentTile(newtile);

        ATile west = old.getNeighbor(Directions.WEST);
        ATile east = old.getNeighbor(Directions.EAST);
        ATile north = old.getNeighbor(Directions.NORTH);
        ATile south = old.getNeighbor(Directions.SOUTH);

        newtile.setNeighbors(north,east,south,west);
        if(north != null)
            north.setNeighbor(newtile, Directions.SOUTH);
        if(south != null)
            south.setNeighbor(newtile, Directions.NORTH);
        if(west != null)
            west.setNeighbor(newtile, Directions.EAST);
        if(east != null)
            east.setNeighbor(newtile, Directions.WEST);

    }

    /**
     * Betölti az adott pályát
     * @param  map A pálya neve
     */
    public void loadMap(String map){
        //region tomb meret meghatarozasa
        int lines = 0, columns = 0;
        FileInputStream fis1 = null;
        try {
            fis1 = new FileInputStream(path + "/maps/" + map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
        try {
            String line;
            char[] chs = null;
            while ((line = br1.readLine()) != null){
                lines++;
                chs = line.toCharArray();
            }
            for (char c : chs) {
                if(c == '.' || c == '+' || c == 'X' || c == 'H' || c == 'M' || c == 'O' || c == 'S' || c == 'T'){
                    columns++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //endregion

        tiles = new ATile[lines][columns];
        visitors = new Visitor[lines][columns];

        //minden tesztesetnél megkapja
        //a teszthez tartozó pálya leírását
        //tartalmazó fájl nevét
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path + "/maps/" + map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        //mezők indexelésére
        int x = 0;
        int y = 0;

        //pályaelemek létrehozása a szövegfájlból
        //a bemeneti nyelvnek megfelelően
        try{
            String line;

            while ((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '.':
                            tiles[x][y] = new Tile();
                            y++;
                            break;
                        case '+':
                            tiles[x][y] = new Obstacle();
                            y++;
                            break;
                        case 'X':
                            tiles[x][y] = new Target();
                            y++;
                            break;
                        case 'H':
                            tiles[x][y] = new Hole();
                            y++;
                            break;
                        case 'M':
                            tiles[x][y] = new Honey();
                            y++;
                            break;
                        case 'O':
                            tiles[x][y] = new Oil();
                            y++;
                            break;
                        case 'S':
                            int sid = Character.getNumericValue(line.charAt(i+1));
                            tiles[x][y] = new Switch(sid);
                            switches.add((Switch) tiles[x][y]);
                            y++;
                            break;
                        case 'T':
                            int tid = Character.getNumericValue(line.charAt(i+1));
                            tiles[x][y] = new Trap(tid);
                            traps.add((Trap)tiles[x][y]);
                            y++;
                            break;
                        case 'W':
                            int wid = Character.getNumericValue(line.charAt(i+1));
                            visitors[x][y] = new Worker(wid);
                            controller.addWorker((Worker)visitors[x][y]);
                            break;
                        case 'B':
                            int bid = Character.getNumericValue(line.charAt(i+1));
                            visitors[x][y] = new Box(bid);
                            boxes.add((Box)visitors[x][y]);
                            break;
                    }
                }
                x++;
                y = 0;
            }
        }
        catch(IOException e) {

        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //visitorok beállítása a mezőkre
        for(int i = 0; i < visitors.length; i++) {
            for(int j = 0; j < visitors[0].length; j++) {
                if(visitors[i][j] != null) {
                    visitors[i][j].setCurrentTile(tiles[i][j]);
                    tiles[i][j].setVisitor(visitors[i][j]);
                }
            }
        }

        //tile-ok szomszédainak beállítása
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[0].length; j++) {
                if(i != tiles.length-1)
                    tiles[i][j].setNeighbor(tiles[i+1][j], Directions.SOUTH);
                if(j != tiles[0].length-1)
                    tiles[i][j].setNeighbor(tiles[i][j+1], Directions.EAST);
            }
        }
        for(int i = tiles.length-1; i >= 0; i--) {
            for(int j = tiles[0].length-1; j >= 0; j--) {
                if(i != 0)
                    tiles[i][j].setNeighbor(tiles[i-1][j], Directions.NORTH);
                if(j != 0)
                    tiles[i][j].setNeighbor(tiles[i][j-1], Directions.WEST);
            }
        }
        for(int i = tiles.length-1; i >= 0; i--) {
            for(int j = 0; j < tiles[0].length; j++) {
                if(i != 0)
                    tiles[i][j].setNeighbor(tiles[i-1][j], Directions.NORTH);
                if(j != tiles[0].length-1)
                    tiles[i][j].setNeighbor(tiles[i][j+1], Directions.EAST);
            }
        }
        for(int i = 0; i < tiles.length; i++) {
            for(int j = tiles[0].length-1; j >= 0; j--) {
                if(i != tiles.length-1)
                    tiles[i][j].setNeighbor(tiles[i+1][j], Directions.SOUTH);
                if(j != 0)
                    tiles[i][j].setNeighbor(tiles[i][j-1], Directions.WEST);
            }
        }

        //switch-trap összerendelés
        for (Switch s :switches) {
            for (Trap t :traps) {
                if(t.getId() == s.getId()){
                    s.setTrap(t);
                }
            }
            if(s.getVisitor() != null){
                Visitor v = s.getVisitor();
                for (Worker w: controller.workers) {
                    if(v == w){
                        s.switchIt(w);
                    }
                    else {
                        s.switchIt(new Box());
                    }
                }
            }
        }

        //inizializáljuk a points tömböt. Minden munkás pontszáma 0 kezdetben
        for(int i = 0; i < this.controller.getNumberOfWorkers(); i++)
            points.add(0);
    }

    /**
     * Kiírja a pályát a konzolra
     */
    public void showMap(){
       for(int i = 0; i < tiles.length; i++){
           for(int j = 0; j < tiles[0].length; j++){
               if(tiles[i][j] != null) {
                   if(tiles[i][j].getVisitor() != null)
                       System.out.print(tiles[i][j].getVisitor().toString());
                   System.out.print(tiles[i][j].toString());
               }
               if(j != tiles[0].length - 1){
                   System.out.print("\t");
               }
           }
           System.out.println();
       }

    }

    /**
     * Visszaadja a mezőket
     *
     * @return a pályát alkotó mezők
     */
    public ATile[][] getMap(){
        return tiles;
    }

    /**
     * Visszaadja a pályához tartozó ablakot
     *
     * @return  A pályához tartozó ablak
     */
    public static GameWindow getWindow(){
        return window;
    }

    /**
     * Elmenti a pályát
     *
     * @param  filename A fájlnév, amin elmentjük a fájlt
     */
    public void saveMap(String filename){
        File f = new File(path + "/Outputs/" + filename);
        FileWriter fw = null;
        try{
            fw = new FileWriter(f);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try{
            for(int i = 0; i < tiles.length; i++){
                for(int j = 0; j < tiles[0].length; j++){
                    if(tiles[i][j] != null) {
                        if(tiles[i][j].getVisitor() != null)
                            bw.write(tiles[i][j].getVisitor().toString());
                        bw.write(tiles[i][j].toString());
                    }
                    if(j != tiles[0].length - 1){
                        bw.write("\t");
                    }
                }
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Eltávolítja a listából egy dobozt. Ha nincs több doboz, akkor véget vet a körnek.
     *
     * @param box Az eltávolítandó doboz
     */
    public void decreaseBoxes(Box box){
        boxes.remove(box);
        if (boxes.isEmpty()){
           this.endRound();
        }
    }

    /**
     * ha elfogytak a dobozok - targetre került vagy meghalt
     * vagy ha meghalt az összes munkás
     * vagy feladta a játékos
     * párbeszédablakban megjelenik a pontszám, majd végetér a program futása
     */

    /**
     * Véget vet a körnek.
     * Akkor hívódik meg, ha nincs annyi doboz, hogy lehetséges lehessen befejezni a játékot, vagy,
     * ha meghalt az összes munkás vagy,
     * ha feladta a játékot.
     * Párbeszédablakban megjelenik a pontszám, majd véget ér a program futása.
     *
     */
    public void endRound(){
        roundover = true;
        int player = this.getCurrentplayer();
        int points = this.getPoints(player);
        String message = "Vége a körnek!\nSzerzett pontok: " + points;
        JOptionPane.showMessageDialog(null, message);
        window.dispose();
    }

    //endregion

    //region A program belépési pontja

    /**
     * A program belépési pontja
     *
     * @param args Args
     */
    public static void main(String[] args) {
        Game game = getInstance();
        game.controller = Controller.getInstance();
        int numeberOfPlayers = 2;

        //parancssorból futtatás elején
        //bekérve a projekt mappa elérési útvonala
        if(args.length > 0){
            path = args[0];
        }else{
            path = System.getProperty("user.dir");
        }
        //ablak létrehozása és megjelenítése
        for (int i=0; i<numeberOfPlayers; i++)
        {
            window = new GameWindow(game);
            window.setVisible(true);
        }




    }

    //endregion

}
