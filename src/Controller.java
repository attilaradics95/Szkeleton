public class Controller {
    /**
     * Singleton metódusok és attributumok
     */
    //Attribútumok
    private static Controller instance = null;
    private List<Worker> workers;
    private Worker selectedWorker;
    privateint availableWorkers;
    private Game game;

    //Függvények
    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }


}
