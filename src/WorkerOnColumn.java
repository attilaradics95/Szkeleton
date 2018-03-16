public class WorkerOnColumn {
    public static void startTest() {
        Directions dir = Directions.EAST;

        Controller controller = null;
        Worker worker = new Worker();
        Worker selectedWorker = new Worker();
        Column next = new Column();
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
