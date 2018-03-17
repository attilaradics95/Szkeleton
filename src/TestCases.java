public class TestCases {

    public void MLL() {
        Directions dir = Directions.EAST;

        Worker worker = new Worker();
        Box box1 = new Box();
        Box box2 = new Box();

        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Tile tile4 = new Tile();

        tile1.setVisitor(worker);
        tile2.setVisitor(box1);
        tile3.setVisitor(box2);
        tile3.setVisitor(null);

        tile1.setNeighbor(tile2, dir);
        tile2.setNeighbor(tile3, dir);
        tile3.setNeighbor(tile4, dir);

        worker.move(dir);
        tile2.accept(worker, dir);
        worker.pushTo(tile2, dir);
        tile3.accept(box1, dir);
        box1.pushTo(tile3, dir);
        tile4.accept(box2, dir);
        box2.pushTo(tile4, dir);

        tile4.setVisitor(box2);
        tile3.setVisitor(box1);
        tile2.setVisitor(worker);
        tile1.setVisitor(null);
    }

    public void MLMMF() {
        Directions dir = Directions.EAST;

        Worker visitor1 = new Worker();
        Box visitor2 = new Box();
        Worker visitor3 = new Worker();
        Worker visitor4 = new Worker();
        /*Visitor visitor2 = null;
        Visitor visitor3 = null;
        Visitor visitor4 = null;*/

        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Tile tile4 = new Tile();
        Wall wall = new Wall();
        /*ATile tile3 = null;
        ATile tile4 = null;
        ATile wall = null;*/

        tile1.setNeighbor(tile2, dir);
        tile2.setNeighbor(tile3, dir);
        tile3.setNeighbor(tile4, dir);
        tile4.setNeighbor(wall, dir);

        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);
        tile3.setVisitor(visitor3);
        tile4.setVisitor(visitor4);


        Controller controller = null;

        controller.moveWorker(dir);
        visitor1.move(dir);
        tile2.accept(visitor1, dir);
        visitor1.pushTo(tile2, dir);

        tile3.accept(visitor2, dir);
        visitor2.pushTo(tile3, dir);

        tile4.accept(visitor3, dir);
        visitor3.pushTo(tile4, dir);

        wall.accept(visitor4, dir);
        visitor4.pushTo(wall, dir);

        visitor4.die();
        controller.eliminateWorker(visitor4);
        tile4.setVisitor(null);
        tile4.setVisitor(visitor3);
        tile3.setVisitor(visitor2);
        tile2.setVisitor(visitor1);
        tile1.setVisitor(null);
    }

    public void WorkerOnColumn() {
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

    public void WorkerOnHole() {
        Worker worker1 = new Worker();
        Hole next = new Hole();
        Tile current = new Tile();

        next.setNeighbor(current, Directions.EAST);

        next.accept(worker1, Directions.EAST);
        worker1.pushTo(next, Directions.EAST);
        current.setVisitor(null);
        worker1.die();
    }

    public void WorkerOnWall() {
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

    public void WorkerPushesBox() {
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

    public void WorkerStandsOnTrap() {
        Directions dir = Directions.EAST;

        Worker worker = new Worker();
        Trap trap = new Trap();

        trap.setOpened(true);
        worker.die();
    }

    public void WorkerStepsOnActiveTrap() {
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

    public void WorkerStepsOnInactiveTrap() {
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
