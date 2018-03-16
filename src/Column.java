public class Column extends ATile {
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");

    }
}
