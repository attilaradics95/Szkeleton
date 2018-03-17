public class WorkerPushesBoxToTarget {
    Controller controller = Controller.getInstance();
    Tile tile1 = new Tile();
    Tile tile2 = new Tile();
    Target tile3 = new Target();
    Worker visitor1 = new Worker();
    Box visitor2 = new Box();

    public void WorkerPushesBoxToTarget() {
        //beállítjuk a mezők szomszédjait a tesztesetnek megfelelően
        tile1.setNeighbors(null, tile2,null,null);
        tile2.setNeighbors(null, tile3,null,tile1);
        tile3.setNeighbors(null, null,null,tile2);

        // beállítjuk a mezők látogatóit a kiindulási állapotnak megfelelően
        tile1.setVisitor(visitor1);
        tile2.setVisitor(visitor2);

        // a teszteset szerint a munkás keletre tolja a ládát eggyel

        controller.moveWorker(Directions.EAST);

    }

}
