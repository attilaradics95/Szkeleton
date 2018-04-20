import java.util.HashMap;

public class Tile extends ATile{

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    //Tabulate tabulate = new Tabulate();

    public Tile() {
        instanceCounter++;
        counter = instanceCounter;
    }

    public void accept(Visitor v, Directions d, int force) {
        //mint minden accept meghívja a visitor pushTo metódusát önmagát átadva
        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d, force);
    }

    //objektum kiíráshoz
    public String toString() {
        return "tile" + counter;
    }
}
