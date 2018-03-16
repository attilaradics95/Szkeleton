public class Controller {
    /**Singleton metódusok és attributumok*/
    private static Controller instance = null;
    private Controller(){}
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }


}
