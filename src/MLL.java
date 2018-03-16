public class MLL {
    public static void main(String[] args) {
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
}
