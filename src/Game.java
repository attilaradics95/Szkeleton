import java.util.List;

public class Game {
    /**Singleton metódusok és attributumok*/
    //Attribútumok
    private static Game instance = null;
    public static int number = 0;
    private Controller controller;
    private List<Tile> tiles;
    private List<Integer> points;
    private int activePlayer;
    private int movableBoxes;
    private Boolean roundOver;
    private Game(){}
    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private void loadMap(){}

    private static void startRound(){
        System.out.println("startRound()");
        while(true){

        }
    }

    public void endRound(){
        System.out.println("endRound()");
    }

    public static void main(String[] args) {
        startRound();
    }
}
