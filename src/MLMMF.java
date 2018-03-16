public class MLMMF {
    public static void startTest() {

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
}
