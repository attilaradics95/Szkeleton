public class WorkerOnHole {

    public static void main(String[] args) {
        Worker worker1 = new Worker();
        Hole next = new Hole();
        Tile current = new Tile();

        next.setNeighbor(current, Directions.EAST);

        next.accept(worker1, Directions.EAST);
        worker1.pushTo(next, Directions.EAST);
        current.setVisitor(null);
        worker1.die();
    }
}
