public class Wall extends ATile {
    public void accept(Visitor v, Direction d) {
        system.out.println("accept(" + v + "," + d + ")");
}
