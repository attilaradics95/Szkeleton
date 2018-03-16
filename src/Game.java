public class Game {
    /**Singleton metódusok és attributumok*/
    private static Game instance = null;
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
