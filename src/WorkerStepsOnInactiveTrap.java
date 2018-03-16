public class WorkerStepsOnInactiveTrap {
    public static void main(String[] args) {
        Directions dir = Directions.EAST;

        Worker worker = new Worker();
        Trap next = new Trap();
        Tile current = new Tile();
        Tile next1 = new Tile();

        current.setVisitor(worker);
        next.setVisitor(null);
        current.setNeighbor(next, dir);

        next.accept(worker, dir);
        worker.pushTo(next, dir);

        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null) {
            //next1 = next.getNeighbor(dir);
            next1.accept(visitorOnNext, dir);
            next1.setVisitor(visitorOnNext);
            next.setVisitor(worker);
            current.setVisitor(null);
        } else {
            current.setVisitor(null);
            next.setVisitor(worker);
        }
    }
}
