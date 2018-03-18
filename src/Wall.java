public class Wall extends ATile {

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    Tabulate tabulate = new Tabulate();

    public Wall() {
        instanceCounter++;
        counter = instanceCounter;
    }

    public void accept(Visitor v, Directions d) {
        tabulate.in();

        System.out.println(this + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);

        tabulate.out();
    }

    public String toString() {
        return "wall" + counter;
    }
}
