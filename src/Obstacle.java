public class Obstacle extends ATile {

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;

    public Obstacle() {
        instanceCounter++;
        counter = instanceCounter;
    }

    public void accept(Visitor v, Directions d, int force) {
        tabulate.in();
        //mint minden accept meghívja a visitor pushTo metódusát önmagát átadva
        System.out.println(this + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d, force);
        tabulate.out();
    }

    //objektum kiíráshoz
    public String toString() {
        return "Obsatcle" + counter;
    }
}