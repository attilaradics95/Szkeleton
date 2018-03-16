public class Column extends ATile {
    public void accept(Visitor v, Directions d, int tabulator) {
        tabulate();
        tabulator++;
        System.out.println("accept(" + v + "," + d + ")");

    }
}
