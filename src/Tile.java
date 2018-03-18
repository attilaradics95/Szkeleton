import java.util.HashMap;

public class Tile extends ATile{

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    Tabulate tabulate = new Tabulate();

    public Tile() {
        instanceCounter++;
        counter = instanceCounter;
    }

    public void accept(Visitor v, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);

        tabulate.out();
    }

    public String toString() {
        return "tile" + counter;
    }
}
