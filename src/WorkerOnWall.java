public class WorkerOnWall {
    public static void startTest() {
        Directions dir = Directions.EAST;

        Controller controller = null;
        Worker worker = new Worker();
        Worker selectedWorker = new Worker();
        Wall next = new Wall();
        Tile tile = new Tile();

        tile.setVisitor(worker);
        tile.setNeighbor(next, dir);

        next.accept(worker, dir);
        worker.pushTo(next, dir);
        selectedWorker = controller.getSelectedworker();
        if(worker == selectedWorker) {
            worker.die();
            controller.eliminateWorker(worker);
        }
    }
}
