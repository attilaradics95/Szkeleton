public class Hole extends ATile {

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;

    public Hole() {
        instanceCounter++;
        counter = instanceCounter;
    }

    public void accept(Visitor v, Directions d) {
        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public String toString() {
        return "hole" + counter;
    }
}
