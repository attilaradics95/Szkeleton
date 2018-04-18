import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    /**Tabulator*/
    Tabulate tabulate = new Tabulate();

    /**Metódusok*/
    private void loadMap(){

    }

    public void decreaseBoxes(Box box){
        boxes.remove(box);
        if (boxes.isEmpty()){
            //mivel csak különálló tesztesetek vizsgálunk ezért nem hívunk endRoundot, ha elfogy az adott tesztben a láda
           // this.endRound();
        }
    }

    private void startRound(){
        System.out.println("startRound()");
        //MAP betöltese
        this.loadMap();

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
        tabulate.in();

        System.out.println("endRound()");
        //Akkor lep ki a loopbol ha megkapja a megfelelo inputot
        while (true) {
            tabulate.tabulate();
            System.out.println("Vége van a körnek? (Y/N)");
            tabulate.tabulate();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                roundover = true;

                tabulate.out();

                return;
            }
            if (input.equals("N") || input.equals("n")) {
                roundover = false;

                tabulate.out();

                return;
            }
        }
    }

    //A PROGRAM BELÉPÉSI PONTJA
    public static void main(String[] args) {
        Game game = getInstance();
        game.controller = Controller.getInstance();
        TestCases tests = new TestCases();


        /**Teszt template */
        System.out.println("Választható tesztesetek:");
        System.out.println("1. Teszt: Worker pushes Box");
        System.out.println("2. Teszt: Worker pushes Box to Target");
        System.out.println("3. Teszt: Worker pushes Box to Column");
        System.out.println("4. Teszt: Worker pushes Box to Switch");
        System.out.println("5. Teszt: Worker pushes Box from Switch");
        System.out.println("6. Teszt: Worker pushes Box to Hole");
        System.out.println("7. Teszt: Worker pushes Box to Wall");
        System.out.println("8. Teszt: Worker pushes another Worker to Tile");
        System.out.println("9. Teszt: Worker pushes another Worker to Hole");
        System.out.println("10. Teszt: Worker steps on Column");
        System.out.println("11. Teszt: Worker steps on Hole");
        System.out.println("12. Teszt: Worker steps on Wall");
        System.out.println("13. Teszt: Worker steps on Target");
        System.out.println("14. Teszt: Worker steps on Switch");
        System.out.println("15. Teszt: Worker steps on Trap");
        System.out.println("16. Teszt: Worker stands on a Trap");
        System.out.println("17. Teszt: Select Worker In Action");
        System.out.println("18. Teszt: Trap Opens With A Box On It ");
        System.out.println("19. Teszt: Worker -> Box -> Box");
        System.out.println("20. Teszt: Worker -> Box -> Worker -> Worker -> Wall");
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
                tests.WorkerPushesBox();
                break;
            case 2:
                tests.WorkerPushesBoxToTarget();
                break;
            case 3:
                tests.WorkerPushesBoxToColumn();
                break;
            case 4:
                tests.WorkerPushesBoxToSwitch();
                break;
            case 5:
                tests.WorkerPushesBoxFromSwitch();
                break;
            case 6:
                tests.WorkerPushesBoxToHole();
                break;
            case 7:
                tests.WorkerPushesBoxToWall();
                break;
            case 8:
                tests.WorkerPushesAnotherWorkerToTile();
                break;
            case 9:
                tests.WorkerPushesAnotherWorkerToHole();
                break;
            case 10:
                tests.WorkerOnColumn();
                break;
            case 11:
                tests.WorkerOnHole();
                break;
            case 12:
                tests.WorkerOnWall();
                break;
            case 13:
                tests.WorkerStepsOnTarget();
                break;
            case 14:
                tests.WorkerStepsOnSwitch();
                break;
            case 15:
                tests.WorkerStepsOnActiveTrap();
                break;
            case 16:
                tests.WorkerStandsOnTrap();
                break;
            case 17:
                tests.SelectWorkerInAction();
                break;
            case 18:
                tests.trapOpensWithABoxOnIt();
                break;
            case 19:
                tests.MLL();
                break;
            case 20:
                tests.MLMMF();
                break;
        }
    }
}
