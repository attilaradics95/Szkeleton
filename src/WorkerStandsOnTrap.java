public class WorkerStandsOnTrap {
    public static void main(String[] args) {
        Directions dir = Directions.EAST;

        Worker worker = new Worker();
        Trap trap = new Trap();

        trap.setOpened(true);
        worker.die();
    }
}
