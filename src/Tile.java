public class Tile {
    public void accept(Visitor v, Direction d) {
        system.out.println("accept(" + v + "," + d + ")");
}
