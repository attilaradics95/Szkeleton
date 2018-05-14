import java.util.ArrayList;

public class Controller {

    //Singleton metódusok és attribútumok
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


     //Attribútumok
    private Game game = null;
    public ArrayList<Worker> workers;
    private Worker selectedworker = null;

    //Metódusok
    /**
     * Munkások számának lekérdezésére szolgáló függvény
     * @return a munkások száma
     */
    public int getNumberOfWorkers() {
        return workers.size();
    }

    /**
     * Hozzáad egy munkást, a munkások listájához.
     *
     * @param w hozzáadandó munkás
     */
    public void addWorker(Worker w) {
        workers.add(w);
        selectedworker = workers.get(0);
    }

    /**
     * Visszaadja a pillanatnyilag kiválasztott munkást
     *
     * @return AZ aktuálisan kiválasztott munkás
     */
    public Worker getSelectedworker() {
        return selectedworker;
    }

    /**
     * Munkás eltávolítása a munkások listájából. Akkor használjuk, amikor meghal egy munkás.
     *
     * @param w Az eltávolítandó munkás.
     */
    public void eliminateWorker(Worker w) {
        //Ha több munkas van mint egy akkor kivesszük a Listabol és szukseg eseten csereljuk a kivalasztottat
        //Ha mar csak az utolso munkas van benne akkor kivesszuk és befejezzuk a kort
        if (workers.size() > 1) {
            if (w == selectedworker) {
                workers.remove(w);
                selectWorker(workers.get(0).getId());
            } else {
                workers.remove(w);
            }

        } else {
            workers.remove(w);
            game.endRound();
        }
    }

    /**
     * Kiválaszt egy munkást. Mindig a kiválasztott munkáson végezzük el a mozgatással kapcsolatos műveleteket.
     *
     * @param i A kiválasztandó munkás azonosítója
     */
    public void selectWorker(int i) {
        System.out.println("selectworker lefutott");
        if(!(workers.isEmpty())){
            selectedworker = null;
            for (Worker w :workers) {
                if(w.id == i){
                    selectedworker = w;

                    //selectedworker.png-t rajzoljuk ki, ha ki lett választva
                    w.view = new WorkerView(true);
                } else {
                    //a többi munkás nem lesz kiválasztva
                    w.view = new WorkerView(false);
                }
            }
            if (selectedworker == null){
                System.out.println("Nincs ilyen id-ju raktaros");
                selectedworker = workers.get(0);
            }
        }
        else{
            System.out.println("Nincs raktáros!");
        }
    }

    /**
     * A kiválasztott munkást mozgatja a game által megadott irányban.
     * Ezzel kezdődik minden mozgatási szekvencia.
     *
     * @param direction mozgatás iránya
     */
    public void moveWorker(Directions direction) {
        if (selectedworker != null){
            selectedworker.move(direction);
        }
        else{
            System.out.println("Nincs kiválasztott raktáros");
        }
    }
}
