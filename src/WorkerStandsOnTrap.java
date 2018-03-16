public class WorkerStandsOnTrap {
    public static void startTest() {
        Directions dir = Directions.EAST;

        Worker worker = new Worker();
        Trap trap = new Trap();

        trap.setOpened(true);
        worker.die();
    }
}
