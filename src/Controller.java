import java.util.ArrayList;

public class Controller {
    /**Singleton metódusok és attributumok*/
    private static Controller instance = null;
    private Controller(){
        workers = new ArrayList<>();
        game = Game.getInstance();
    }
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**Attribútumok*/
    private Game game = null;
    private ArrayList<Worker> workers;
    private Worker selectedworker = null;

    /**Metódusok*/
    public void selectWorker(int i){
        System.out.println("selectWorker");
        //TODO
    }
    public void moveWorker(Directions direction){
        System.out.println("selectWorker");
        //TODO
    }
}
