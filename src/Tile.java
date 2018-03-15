public class Tile {
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
    }
