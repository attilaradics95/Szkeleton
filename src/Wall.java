public class Wall extends ATile {

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    //Tabulate tabulate = new Tabulate();

    public Wall() {
        instanceCounter++;
        counter = instanceCounter;
    }

    public void accept(Visitor v, Directions d) {
        tabulate.in();

        //mint minden accept meghívja a visitor pushTo metódusát önmagát átadva
        System.out.println(this + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);

        tabulate.out();
    }

    //objektum kiíráshoz
    public String toString() {
        return "wall" + counter;
    }
}
