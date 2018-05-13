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
    //és ide lesznek elmentve is
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
    /**Metódusok*/
    //Kicserél egy mezőt
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
        // a teszthez tartozó pálya leírását
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
    }

    //pálya kiíratása konzolra
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

    public ATile[][] getMap(){
        return tiles;
    }

    public static GameWindow getWindow(){
        return window;
    }

    //pálya mentése fájlba
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

    public void decreaseBoxes(Box box){
        boxes.remove(box);
        if (boxes.isEmpty()){
            //mivel csak különálló tesztesetek vizsgálunk ezért nem hívunk endRoundot, ha elfogy az adott tesztben a láda
           this.endRound();
        }
    }

    private void startRound(String map){
        //MAP betöltese
        loadMap(map);

        //Loop az inputok kezelesere
        while(!roundover){

        }
    }

    public void endRound(){
        roundover = true;
    }

    //endregion

    //region A program belépési pontja
    //A PROGRAM BELÉPÉSI PONTJA
    public static void main(String[] args) {
        Game game = getInstance();
        game.controller = Controller.getInstance();

        //parancssorból futtatás elején
        //bekérve a projekt mappa elérési útvonala
        if(args.length > 0){
            path = args[0];
        }else{
            path = System.getProperty("user.dir");
        }

        window = new GameWindow(game);
        window.setVisible(true);
        window.addKeyListener(new MyListener());
    }

    //endregion

}
