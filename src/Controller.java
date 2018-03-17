import java.util.ArrayList;

public class Controller {
    /**
     * Singleton metódusok és attributumok
     */
    private static Controller instance = null;

    private Controller() {
        workers = new ArrayList<>();
        game = Game.getInstance();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Attribútumok
     */
    private Game game = null;
    private ArrayList<Worker> workers;
    private Worker selectedworker = null;

    /**
     * Metódusok
     */
    public void addWorker(Worker w) {
        workers.add(w);
        selectedworker = workers.get(0);
    }

    public Worker getSelectedworker() {
        return selectedworker;
    }

    public void eliminateWorker(Worker w) {
        System.out.println("eliminateWorker()");
        //Ha több munkas van mint egy akkor kivesszük a Listabol és szukseg eseten csereljuk a kivalasztottat
        //Ha mar csak az utolso munkas van benne akkor kivesszuk és befejezzuk a kort
        if (workers.size() > 1) {
            if (w == selectedworker) {
                workers.remove(w);
                selectedworker = workers.get(0);
            } else {
                workers.remove(w);
            }
        } else {
            workers.remove(w);
            game.endRound();
        }
    }

    public void selectWorker(int i) {
        System.out.println("selectWorker");
        selectedworker = workers.get(i);
    }

    public void moveWorker(Directions direction) {
        System.out.println("selectWorker");
        selectedworker.move(direction);
    }
}
