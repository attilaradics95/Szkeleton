public class WorkerPushesBox {
    public static void startTest() {
        Directions dir = Directions.EAST;

        Controller controller = null;

        Worker worker = new Worker();
        Tile current = new Tile();
        Tile next = new Tile();
        Tile next1 = new Tile();
        Box box = new Box();

        current.setVisitor(worker);
        next.setVisitor(box);
        next1.setVisitor(null);
        current.setNeighbor(next, dir);
        next.setNeighbor(next1, dir);

        controller.moveWorker(dir);
        worker.move(dir);
        //next = current.getNeighbor(dir);
        next.accept(worker, dir);
        worker.pushTo(next, dir);

        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null) {
            //next1 = next.getNeighbor(dir);
            next1.accept(visitorOnNext, dir);
            box.pushTo(next1, dir);
            next1.setVisitor(visitorOnNext);
            next.setVisitor(worker);
            current.setVisitor(null);
        } else {
            next.setVisitor(worker);
            current.setVisitor(null);
        }
    }
}
