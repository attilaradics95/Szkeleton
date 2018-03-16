public class WorkerStepsOnActiveTrap {
    public static void startTest() {
        Directions dir = Directions.EAST;

        Worker worker = new Worker();
        Trap next = new Trap();
        Tile current = new Tile();

        current.setVisitor(worker);
        next.setVisitor(null);
        current.setNeighbor(next, dir);

        next.accept(worker, dir);
        worker.pushTo(next, dir);
        current.setVisitor(null);
        worker.die();
    }
}
