import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    /**Singleton metódusok és attributumok*/
    private static Game instance = null;
    private Game(){
        boxes = new ArrayList<>();
    }
    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**Attributumok*/
    private Controller controller = null;
    private boolean roundover = false;
    private ArrayList<Box> boxes;

    // később két játékos esetén mindkettő pontját tárolni kell majd,
    // ezért szükséges a list
   // private List<Integer> points = new List<Integer>();
    // de most elég egy intben tárolni a teszteseteknek megfelelően
    private int points = 0;

    //projekt mappa elérési útvonala
    //ezen a mappán belül lesznek a bemeneti pályák
    //és ide lesznek elmentve is
    private static String path;

    //ebben tároljuk el a fájlból beolvasott dolgokat
    private ATile[][] tiles;
    private Visitor[][] visitors;

    //pontok növelése
    public void addPoint(){
        points++;
    }

    //pontok kiíratásához lekérdező fv
    public int getPoints(){
        return points;
    }

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
    }
    public void loadMap(String map){
        //minden tesztesetnél megkapja
        // a teszthez tartozó pálya leírását
        //tartalmazó fájl nevét
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path + "/Inputs/" + map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        //mezők indexelésére
        int x = 0;
        int y = 0;

        //visitorok indexelésére
        int l = 0;
        int m = 0;

        try{
            String line = null;



            while ((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '.':
                            tiles[x][y] = new Tile();
                            x++;
                            l++;
                            break;
                        case '+':
                            tiles[x][y] = new Obstacle();
                            x++;
                            l++;
                            break;
                        case 'X':
                            tiles[x][y] = new Target();
                            x++;
                            l++;
                            break;
                        case 'H':
                            tiles[x][y] = new Hole();
                            x++;
                            l++;
                            break;
                        case 'M':
                            tiles[x][y] = new Honey();
                            x++;
                            l++;
                            break;
                        case 'O':
                            tiles[x][y] = new Oil();
                            x++;
                            l++;
                            break;
                        case 'S':
                            tiles[x][y] = new Switch((int)line.charAt(i+1));
                            x++;
                            l++;
                            break;
                        case 'T':
                            tiles[x][y] = new Trap((int)line.charAt(i+1));
                            x++;
                            l++;
                            break;
                        case 'W':
                            visitors[l][m] = new Worker();
                            visitors[l][m].id = line.charAt(i+1);
                            break;
                        case 'B':
                            visitors[l][m] = new Box();
                            visitors[l][m].id = line.charAt(i+1);
                            break;
                    }
                }
                y++;
                m++;
            }
        } catch(IOException e) {

        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //visitorok beállítása a mezőkre
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < m; j++) {
                visitors[i][j].setCurrentTile(tiles[i][j]);
            }
        }

        //tile-ok szomszédainak beállítása
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                if(i > 0 && i < x-1 && j > 0 && j < y-1) {
                    tiles[i][j].setNeighbors(tiles[i][j-1], tiles[i-1][j], tiles[i][j+1], tiles[i+1][j]);
                }
            }
        }

    }

    //pálya kiíratása konzolra
    public void showMap(){
       for(int i = 0; i < tiles.length; i++){
           for(int j = 0; j < tiles[0].length; j++){
               if(visitors[i][j] != null)
                   System.out.print(visitors[i][j].toString());
               System.out.print(tiles[i][j].toString());
               if(j != tiles[0].length - 1){
                   System.out.print("\t");
               }
           }
           System.out.println();
       }

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
                    if(visitors[i][j] != null)
                        bw.write(visitors[i][j].toString());
                    bw.write(tiles[i][j].toString());
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

    private void startRound(){
        System.out.println("startRound()");
        //MAP betöltese
        //this.loadMap();

        //Loop az inputok kezelesere
        while(!roundover){
            //Menu kiirasa
            System.out.println("1.Irány megadása(W,A,S,D)");
            System.out.println("2.Raktáros kiválasztása(0,1,2...99)");
            System.out.println("3.Feladás(igiveup)");
            System.out.println("Kérem adja meg az inputot:");
            //Input beolvasása a konzolról
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Regularis kifejezesek az input csekkolasara
            Pattern selectPattern = Pattern.compile("^([0-9]|[1-9][0-9])$");
            Pattern movePattern = Pattern.compile("^(W|A|S|D|w|a|s|d)$");
            Matcher selectMatch = selectPattern.matcher(input);
            Matcher moveMatch = movePattern.matcher(input);

            if(selectMatch.matches()){
                controller.selectWorker(Integer.parseInt(input));
            }
            if(moveMatch.matches()){
                switch (input){
                    case "W":
                    case "w":
                        controller.moveWorker(Directions.NORTH);
                        break;
                    case "D":
                    case "d":
                        controller.moveWorker(Directions.EAST);
                        break;
                    case "S":
                    case "s":
                        controller.moveWorker(Directions.SOUTH);
                        break;
                    case "A":
                    case "a":
                        controller.moveWorker(Directions.WEST);
                        break;
                }
            }
            if (input.equals("igiveup")){
                this.endRound();
            }
            else{
                System.out.println("Az input nem valid!");
            }
        }
    }

    public void endRound(){
        roundover = true;
    }

    //A PROGRAM BELÉPÉSI PONTJA
    public static void main(String[] args) {
        Game game = getInstance();
        game.controller = Controller.getInstance();
        TestCases tests = new TestCases();

        //parancssorból futtatás elején
        //bekérve a projekt mappa elérési útvonala
        if(args.length > 0){
            path = args[0];
        }


        /**Teszt template */
        System.out.println("Választható tesztesetek:");
        System.out.println("1. Teszt: Moving a worker");
        System.out.println("2. Teszt: Select worker");
        System.out.println("3. Teszt: Killing a worker");
        System.out.println("4. Teszt: Destruction of box");
        System.out.println("5. Teszt: Box to Target");
        System.out.println("6. Teszt: Game over");
        System.out.println("7. Teszt: Switch");
        System.out.println("8. Teszt: Put oil");
        System.out.println("9. Teszt: Put honey");
        System.out.println("10. Teszt: Worker steps on active Trap");
        System.out.println("11. Teszt: Worker steps on inactive Trap");
        System.out.println("12. Teszt: Worker steps on Obstacle");
        System.out.println("13. Teszt: Worker steps on Switch");
        System.out.println("14. Teszt: Worker steps on Target");
        System.out.println("15. Teszt: Worker steps on Hole");
        System.out.println("16. Teszt: Worker pushes another Worker to Tile");
        System.out.println("17. Teszt: Worker pushes Box to Tile");
        System.out.println("18. Teszt: Worker pushes Box to inactive Trap ");
        System.out.println("19. Teszt: Worker pushes Box to active Trap");
        System.out.println("20. Teszt: Worker pushes Box to Switch");
        System.out.println("21. Teszt: Worker pushes Box to Hole");
        System.out.println("22. Teszt: Worker pushes Box to Obstacle");
        System.out.println("23. Teszt: Worker -> Worker -> Box");
        System.out.println("24. Teszt: Worker -> Box -> Worker");
        System.out.println("Teszt kiválasztása(1,2,...):");



        /**
         *
         *  /\
         *  |
         *  |
         *  |
         * IDE PRINTELJETEK KI LEGYSZI A TESZTEKET AMIKET MEGIRTATOK EGY SZAMMAL AZ ELEJEN ÉS RAKJATOK BE A SWITCHBE PLS
         */


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (Integer.parseInt(input)){
            case 1:
                tests.MoveWorker();
                break;
            case 2:
                tests.SelectWorker();
                break;
            case 3:
                tests.KillWorker();
                break;
            case 4:
                tests.DestructionofBox();
                break;
            case 5:
                tests.BoxtoTarget();
                break;
            case 6:
                tests.GameOver();
                break;
            case 7:
                tests.Switch();
                break;
            case 8:
                tests.PutOil();
                break;
            case 9:
                tests.PutHoney();
                break;
            case 10:
                tests.WorkerStepsOnActiveTrap();
                break;
            case 11:
                tests.WorkerStepsOnInactiveTrap();
                break;
            case 12:
                tests.WorkerStepsOnObstacle();
                break;
            case 13:
                tests.WorkerStepsOnSwitch();
                break;
            case 14:
                tests.WorkerStepsOnTarget();
                break;
            case 15:
                tests.WorkerStepsOnHole();
                break;
            case 16:
                tests.WorkerPushesWorkerToTile();
                break;
            case 17:
                tests.WorkerPushesBoxToTile();
                break;
            case 18:
                tests.WorkerPushesBoxToInactiveTrap();
                break;
            case 19:
                tests.WorkerPushesBoxToActiveTrap();
                break;
            case 20:
                tests.WorkerPushesBoxToSwitch();
                break;
            case 21:
                tests.WorkerPushesBoxToHole();
                break;
            case 22:
                tests.WorkerPushesBoxToObstacle();
                break;
            case 23:
                tests.MML();
                break;
            case 24:
                tests.MLM();
                break;
        }
    }
}
