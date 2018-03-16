import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    /**Singleton metódusok és attributumok*/
    private static Game instance = null;
    private Game(){
        controller = Controller.getInstance();
    }
    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**Attributumok*/
    Controller controller = null;
    boolean roundover = false;



    /**Privat Metódusok*/
    private void loadMap(){}

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
        System.out.println("endRound()");
        //Akkor lep ki a loopbol ha megkapja a megfelelo inputot
        while (true) {
            System.out.println("Vége van a körnek? (Y/N)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                roundover = true;
                return;
            }
            if (input.equals("N") || input.equals("n")) {
                roundover = false;
                return;
            }
        }
    }

    public static void main(String[] args) {
        Game game = getInstance();

        game.startRound();
    }
}
