import java.util.ArrayList;

public class Controller {
    /**
     * Singleton metódusok és attributumok
     */
    private static Controller instance = null;

    Tabulate tabulate = new Tabulate();

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
        tabulate.in();

        System.out.println("eliminateWorker(" + w + ")");
        //Ha több munkas van mint egy akkor kivesszük a Listabol és szukseg eseten csereljuk a kivalasztottat
        //Ha mar csak az utolso munkas van benne akkor kivesszuk és befejezzuk a kort
        if (workers.size() > 1) {
            if (w == selectedworker) {
                workers.remove(w);
                selectedworker = workers.get(0);
            } else {
                workers.remove(w);
            }

            tabulate.out();

        } else {
            workers.remove(w);
            game.endRound();

            tabulate.out();
        }
    }

    public void selectWorker(int i) {
        if(!(workers.isEmpty())){
           // 0val kezdődik a raktárosok számozása!
            if(i-1 >= workers.size()){
                System.out.println("Nincs ilyen sorszámú raktáros. Legnagyobb sorszámú:" + (workers.size() - 1));
            }
            else{
                // 0val kezdődik a raktárosok számozása!
                selectedworker = workers.get(i-1);
                System.out.println("selectWorker(" + i + ")");
            }
        }
        else{
            System.out.println("Nincs raktáros!");
        }
    }

    public void moveWorker(Directions direction) {
        System.out.println("moveWorker(" + direction + ")");
        if (selectedworker != null){
            selectedworker.move(direction);
        }
        else{
            System.out.println("Nincs kiválasztott raktáros");
        }
    }
}
