public class Tile {
    public void accept(Visitor v, Directions d) {
        system.out.println("accept(" + v + "," + d + ")");
}
