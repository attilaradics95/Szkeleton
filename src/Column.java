public class Column {
    public void accept(Visitor v, Direction d) {
        system.out.println("accept(" + v + "," + d + ")");
}
